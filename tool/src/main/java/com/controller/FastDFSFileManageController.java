package com.controller;

import com.enums.FileResponseEnum;
import com.model.dto.DownloadFormDTO;
import com.model.entity.FastDFSFile;
import com.model.vo.ResponseVO;
import com.util.FileUtil;
import com.service.FastDFSFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:06 PM
 */
@Slf4j
@Validated
@RestController
@Api(tags = "文件模块")
@RequestMapping("/fastdfs_file")
public class FastDFSFileManageController {

    private final FastDFSFileService fastDFSFileService;

    @Autowired
    public FastDFSFileManageController(FastDFSFileService fastDFSFileService) {
        this.fastDFSFileService = fastDFSFileService;
    }

    @ApiOperation(value = "上传图片")
    @PostMapping("/image/upload")
    public ResponseVO<FastDFSFile> uploadImage(@ApiParam(name = "file", value = "待上传图片", required = true)
                                               @NotNull @RequestPart(value = "file") MultipartFile multipartFile) {
        log.info("上传图片");
        if (multipartFile.isEmpty() || !FileUtil.isImage(multipartFile)) {
            log.warn("上传文件类型错误");
            return new ResponseVO<>(FileResponseEnum.FILE_CONTENT_TYPE_ERROR);
        }
        if (multipartFile.getSize() < 1024 * 10) {
            return fastDFSFileService.uploadImageAndCrtThumbImage(multipartFile);
        }
        return fastDFSFileService.uploadImage(multipartFile);
    }

    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public ResponseVO<FastDFSFile> uploadFile(@ApiParam(name = "file", value = "待上传文件", required = true)
                                              @NotNull @RequestPart(value = "file") MultipartFile multipartFile) {
        log.info("上传文件");
        return fastDFSFileService.uploadFile(multipartFile);
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/download")
    public ResponseVO downloadFile(@ApiParam(name = "fileUrl", value = "文件路径", required = true)
                                   @NotBlank @RequestParam("fileUrl") String fileUrl,
                                   HttpServletResponse response) {
        log.info("下载文件");
        return fastDFSFileService.downloadFile(fileUrl, response);
    }

    @ApiOperation(value = "下载文件")
    @PostMapping("/download")
    public ResponseVO downloadFile(@ApiParam(name = "downloadFormDTO", value = "文件下载数据传输模型", required = true)
                                   @Validated @RequestBody DownloadFormDTO downloadFormDTO,
                                   HttpServletResponse response) {
        log.info("下载文件");
        return fastDFSFileService.downloadFile(downloadFormDTO.getFileUrl(), response);
    }

}
