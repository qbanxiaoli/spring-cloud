package com.qbanxiaoli.tool.service.Impl;


import com.qbanxiaoli.tool.bean.FastDFSClient;
import com.qbanxiaoli.common.enums.response.CommonResponseEnum;
import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.FileUtil;
import com.qbanxiaoli.tool.dao.repository.FastDFSFileRepository;
import com.qbanxiaoli.tool.model.entity.FastDFSFile;
import com.qbanxiaoli.tool.service.FastDFSFileService;
import lombok.extern.slf4j.Slf4j;
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

    private final FastDFSFileRepository fastDFSFileRepository;

    @Autowired
    public FastDFSFileServiceImpl(FastDFSFileRepository fastDFSFileRepository) {
        this.fastDFSFileRepository = fastDFSFileRepository;
    }

    /**
     * @param multipartFile 待上传文件
     * @return 请求响应
     * @author qbanxiaoli
     * @description 上传文件
     */
    @Override
    public ResponseVO<FastDFSFile> uploadFile(MultipartFile multipartFile) {
        //上传图片
        String storePath = FastDFSClient.uploadFile(multipartFile);
        log.info("上传文件地址为：" + FastDFSClient.getResAccessUrl(storePath));
        FastDFSFile fastDFSFile = new FastDFSFile();
        //设置文件地址
        fastDFSFile.setStorePath(storePath);
        //设置文件服务器访问地址
        fastDFSFile.setWebServerUrl(FastDFSClient.getWebServerUrl());
        //设置文件类型
        fastDFSFile.setContentType(multipartFile.getContentType());
        //设置文件名
        fastDFSFile.setFileName(multipartFile.getOriginalFilename());
        //设置文件扩展名
        fastDFSFile.setFileExtension(FileUtil.getFileExtension(multipartFile));
        //设置文件大小
        fastDFSFile.setFileSize(multipartFile.getSize());
        //实例化到数据库
        fastDFSFileRepository.save(fastDFSFile);
        return new ResponseVO<>(CommonResponseEnum.SUCCESS, fastDFSFile);
    }

    @Override
    public ResponseVO downloadFile(String fileUrl, HttpServletResponse response) {
        log.info("文件访问地址为：" + FastDFSClient.getResAccessUrl(fileUrl));
        //查询文件信息
        FastDFSFile fastDFSFile = fastDFSFileRepository.findByStorePath(fileUrl);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fastDFSFile.getFileName().getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes = FastDFSClient.downloadFile(fileUrl);
        //利用字节数组输入流录入字节数组
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
