package com.qbanxiaoli.sms.service;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.sms.model.entity.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:54 PM
 */
public interface FileService {

    /**
     * @param multipartFile 待上传图片
     * @return 请求响应
     * @author qbanxiaoli
     * @description 上传图片
     */
    ResponseVO<File> uploadImage(MultipartFile multipartFile);
}
