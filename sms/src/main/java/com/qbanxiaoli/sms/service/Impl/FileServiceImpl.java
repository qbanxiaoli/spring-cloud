package com.qbanxiaoli.sms.service.Impl;

import com.qbanxiaoli.common.bean.FastDFSClient;
import com.qbanxiaoli.common.enums.CommonResponseEnum;
import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.FileUtil;
import com.qbanxiaoli.sms.dao.repository.FileRepository;
import com.qbanxiaoli.sms.dao.repository.SmsRepository;
import com.qbanxiaoli.sms.model.entity.File;
import com.qbanxiaoli.sms.service.FileService;
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
public class FileServiceImpl implements FileService {


    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * @param multipartFile 待上传图片
     * @return 请求响应
     * @author qbanxiaoli
     * @description 上传图片
     */
    @Override
    public ResponseVO<File> uploadImage(MultipartFile multipartFile) {
        //上传图片
        String storePath = FastDFSClient.uploadFile(multipartFile);
        log.info("上传图片地址为：" + FastDFSClient.getResAccessUrl(storePath));
        File file = new File();
        //设置图片地址
        file.setStorePath(storePath);
        //设置文件服务器访问地址
        file.setWebServerUrl(FastDFSClient.getWebServerUrl());
        //设置文件类型
        file.setContentType(multipartFile.getContentType());
        //设置文件后缀名
        file.setFileExtension(FileUtil.getFileExtension(multipartFile));
        //设置文件大小
        file.setFileSize(multipartFile.getSize());
        //实例化到数据库
        fileRepository.save(file);
        return new ResponseVO<>(CommonResponseEnum.SUCCESS, file);
    }

}
