package com.qbanxiaoli.tool.model.converter;

import com.qbanxiaoli.common.util.FileUtil;
import com.qbanxiaoli.tool.bean.FastDFSClient;
import com.qbanxiaoli.tool.model.dto.SmsFormDTO;
import com.qbanxiaoli.tool.model.entity.FastDFSFile;
import com.qbanxiaoli.tool.model.vo.SendSmsResponseVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qbanxiaoli
 * @description
 * @create 2019-01-09 10:23
 */
public class FastDFSFileAssembly {

    public static FastDFSFile toDomain(String storePath, MultipartFile multipartFile) {
        if (storePath == null || multipartFile == null) {
            return null;
        }
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
        return fastDFSFile;
    }

}
