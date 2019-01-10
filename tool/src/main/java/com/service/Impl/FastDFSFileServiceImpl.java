package com.service.Impl;


import com.bean.FastDFSClient;
import com.dao.repository.FastDFSFileRepository;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.model.converter.FastDFSFileAssembly;
import com.model.entity.FastDFSFile;
import com.enums.response.CommonResponseEnum;
import com.model.vo.ResponseVO;
import com.util.FileUtil;
import com.service.FastDFSFileService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:57 PM
 */
@Slf4j
@Service
public class FastDFSFileServiceImpl implements FastDFSFileService {

    private final ThumbImageConfig thumbImageConfig;

    private final FastDFSFileRepository fastDFSFileRepository;

    @Autowired
    public FastDFSFileServiceImpl(ThumbImageConfig thumbImageConfig,
                                  FastDFSFileRepository fastDFSFileRepository) {
        this.thumbImageConfig = thumbImageConfig;
        this.fastDFSFileRepository = fastDFSFileRepository;
    }

    /**
     * @param multipartFile 待上传文件
     * @return 请求响应
     * @author qbanxiaoli
     * @description 上传文件
     */
    @Override
    public ResponseVO<FastDFSFile> uploadImage(MultipartFile multipartFile) {
        log.info("上传图片大小：" + multipartFile.getSize() + "b");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(multipartFile.getInputStream())
                    .size(thumbImageConfig.getWidth(), thumbImageConfig.getHeight())
                    .outputQuality(0.5f)//图片质量
                    .toOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("压缩后图片大小：" + byteArrayOutputStream.toByteArray().length + "b");
        // 上传压缩后的图片
        String storePath = FastDFSClient.uploadFile(byteArrayOutputStream.toByteArray(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        log.info("上传文件地址为：" + FastDFSClient.getResAccessUrl(storePath));
        FastDFSFile fastDFSFile = FastDFSFileAssembly.toDomain(storePath, multipartFile);
        if (fastDFSFile == null) {
            log.error("上传图片失败");
            return new ResponseVO<>(CommonResponseEnum.FAILURE);
        }
        // 实例化到数据库
        fastDFSFileRepository.save(fastDFSFile);
        return new ResponseVO<>(CommonResponseEnum.SUCCESS, fastDFSFile);
    }

    @Override
    public ResponseVO<FastDFSFile> uploadImageAndCrtThumbImage(MultipartFile multipartFile) {
        log.info("上传图片大小：" + multipartFile.getSize() + "b");
        String storePath = FastDFSClient.uploadImageAndCrtThumbImage(multipartFile);
        log.info("上传缩略图地址为：" + FastDFSClient.getResAccessUrl(storePath));
        FastDFSFile fastDFSFile = FastDFSFileAssembly.toDomain(storePath, multipartFile);
        if (fastDFSFile == null) {
            log.error("上传缩略图失败");
            return new ResponseVO<>(CommonResponseEnum.FAILURE);
        }
        // 实例化到数据库
        fastDFSFileRepository.save(fastDFSFile);
        return new ResponseVO<>(CommonResponseEnum.SUCCESS, fastDFSFile);
    }

    @Override
    public ResponseVO<FastDFSFile> uploadFile(MultipartFile multipartFile) {
        // 上传文件
        String storePath = FastDFSClient.uploadFile(multipartFile);
        log.info("上传文件地址为：" + FastDFSClient.getResAccessUrl(storePath));
        FastDFSFile fastDFSFile = FastDFSFileAssembly.toDomain(storePath, multipartFile);
        if (fastDFSFile == null) {
            log.error("上传文件失败");
            return new ResponseVO<>(CommonResponseEnum.FAILURE);
        }
        // 实例化到数据库
        fastDFSFileRepository.save(fastDFSFile);
        return new ResponseVO<>(CommonResponseEnum.SUCCESS, fastDFSFile);
    }

    @Override
    public ResponseVO downloadFile(String fileUrl, HttpServletResponse response) {
        log.info("文件访问地址为：" + FastDFSClient.getResAccessUrl(fileUrl));
        // 查询文件信息
        FastDFSFile fastDFSFile = fastDFSFileRepository.findByStorePath(fileUrl);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fastDFSFile.getFileName().getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes = FastDFSClient.downloadFile(fileUrl);
        // 利用字节数组输入流录入字节数组
        if (bytes == null) {
            log.error("下载文件失败");
            return new ResponseVO(CommonResponseEnum.FAILURE);
        }
        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(bytes));
        try {
            FileUtil.fileUpload(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
