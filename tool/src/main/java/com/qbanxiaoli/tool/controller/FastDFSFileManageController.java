package com.qbanxiaoli.tool.controller;

import com.qbanxiaoli.common.bean.FastDFSClient;
import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.FileUtil;
import com.qbanxiaoli.tool.enums.SmsResponseEnum;
import com.qbanxiaoli.tool.model.dto.DownloadFormDTO;
import com.qbanxiaoli.tool.model.entity.FastDFSFile;
import com.qbanxiaoli.tool.service.FastDFSFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:06 PM
 */
@Slf4j
@RestController
@Api(tags = "文件写模块")
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
                                               @RequestPart(value = "file") MultipartFile multipartFile) {
        log.info("上传图片");
        if (multipartFile.isEmpty() || !FileUtil.isImage(multipartFile)) {
            log.warn("上传文件类型错误");
            return new ResponseVO<>(SmsResponseEnum.FILE_CONTENT_TYPE_ERROR);
        }
        return fastDFSFileService.uploadImage(multipartFile);
    }

    @ApiOperation(value = "下载文件")
    @PostMapping("/download")
    public void downloadFile(@ApiParam(name = "downloadFormDTO", value = "文件下载数据传输模型", required = true)
                             @Validated @RequestBody DownloadFormDTO downloadFormDTO,
                             HttpServletResponse response) {
        log.info("下载文件");
        String fileUrl = downloadFormDTO.getFileUrl();
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        byte[] bytes = FastDFSClient.downloadFile(fileUrl);
        InputStream inputStream = null;
        //利用字节数组输入流录入字节数组
        if (bytes != null) {
            inputStream = new BufferedInputStream(new ByteArrayInputStream(bytes));
        }
        try {
            FileUtil.fileUpload(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
