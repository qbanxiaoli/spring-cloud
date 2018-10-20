package com.qbanxiaoli.sms.controller;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.FileUtil;
import com.qbanxiaoli.sms.enums.SmsResponseEnum;
import com.qbanxiaoli.sms.model.entity.File;
import com.qbanxiaoli.sms.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:06 PM
 */
@Slf4j
@RestController
@Api(tags = "上传文件")
@RequestMapping("/file")
public class FileManageController {

    private final FileService fileService;

    @Autowired
    public FileManageController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "上传图片")
    @PostMapping("/image/upload")
    public ResponseVO<File> uploadImage(@ApiParam(name = "file", value = "待上传图片", required = true)
                                        @RequestPart(value = "file") MultipartFile multipartFile) {
        log.info("上传图片");
        if (multipartFile.isEmpty() || !FileUtil.isImage(multipartFile)) {
            log.warn("上传文件类型错误");
            return new ResponseVO<>(SmsResponseEnum.FILE_CONTENT_TYPE_ERROR);
        }
        return fileService.uploadImage(multipartFile);
    }

}
