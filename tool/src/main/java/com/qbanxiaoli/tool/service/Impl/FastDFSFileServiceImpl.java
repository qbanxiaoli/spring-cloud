package com.qbanxiaoli.tool.service.Impl;

import com.qbanxiaoli.common.bean.FastDFSClient;
import com.qbanxiaoli.common.enums.CommonResponseEnum;
import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.FileUtil;
import com.qbanxiaoli.tool.dao.repository.FastDFSFileRepository;
import com.qbanxiaoli.tool.model.entity.FastDFSFile;
import com.qbanxiaoli.tool.service.FastDFSFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:57 PM
 */
@Slf4j
@Service
@Transactional
public class FastDFSFileServiceImpl implements FastDFSFileService {

    private final FastDFSFileRepository fastDFSFileRepository;

    @Autowired
    public FastDFSFileServiceImpl(FastDFSFileRepository fastDFSFileRepository) {
        this.fastDFSFileRepository = fastDFSFileRepository;
    }

    /**
     * @param multipartFile 待上传图片
     * @return 请求响应
     * @author qbanxiaoli
     * @description 上传图片
     */
    @Override
    public ResponseVO<FastDFSFile> uploadImage(MultipartFile multipartFile) {
        //上传图片
        String storePath = FastDFSClient.uploadFile(multipartFile);
        log.info("上传图片地址为：" + FastDFSClient.getResAccessUrl(storePath));
        FastDFSFile fastDFSFile = new FastDFSFile();
        //设置图片地址
        fastDFSFile.setStorePath(storePath);
        //设置文件服务器访问地址
        fastDFSFile.setWebServerUrl(FastDFSClient.getWebServerUrl());
        //设置文件类型
        fastDFSFile.setContentType(multipartFile.getContentType());
        //设置文件扩展名
        fastDFSFile.setFileExtension(FileUtil.getFileExtension(multipartFile));
        //设置文件大小
        fastDFSFile.setFileSize(multipartFile.getSize());
        //实例化到数据库
        fastDFSFileRepository.save(fastDFSFile);
        return new ResponseVO<>(CommonResponseEnum.SUCCESS, fastDFSFile);
    }

}
