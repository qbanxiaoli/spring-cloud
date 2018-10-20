package com.qbanxiaoli.sms.service.Impl;

import com.qbanxiaoli.common.bean.FastDFSClient;
import com.qbanxiaoli.common.enums.CommonResponseEnum;
import com.qbanxiaoli.common.model.vo.ResponseVO;
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
        String path = FastDFSClient.uploadFile(multipartFile);
        File file = new File();
        file.setPath(path);
        return new ResponseVO<>(CommonResponseEnum.SUCCESS, file);
    }

}
