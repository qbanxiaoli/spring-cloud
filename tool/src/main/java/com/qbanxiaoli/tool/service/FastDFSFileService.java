package com.qbanxiaoli.tool.service;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.tool.model.entity.FastDFSFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:54 PM
 */
public interface FastDFSFileService {

    /**
     * @param multipartFile 待上传图片
     * @return 请求响应
     * @author qbanxiaoli
     * @description 上传图片
     */
    ResponseVO<FastDFSFile> uploadImage(MultipartFile multipartFile);

}
