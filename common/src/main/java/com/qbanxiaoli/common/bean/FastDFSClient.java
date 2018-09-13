package com.qbanxiaoli.common.bean;

import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/9/7 下午7:47
 */
@Slf4j
@Component
public class FastDFSClient {

    private final FastFileStorageClient fastFileStorageClient;

    private final FdfsWebServer fdfsWebServer;

    @Autowired
    public FastDFSClient(FastFileStorageClient fastFileStorageClient, FdfsWebServer fdfsWebServer) {
        this.fastFileStorageClient = fastFileStorageClient;
        this.fdfsWebServer = fdfsWebServer;
    }

    /**
     * @param multipartFile 文件对象
     * @return 返回文件地址
     * @author qbanxiaoli
     * @description 上传文件
     */
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    /**
     * @param multipartFile 图片对象
     * @return 返回图片地址
     * @author qbanxiaoli
     * @description 上传缩略图
     */
    public String uploadImageAndCrtThumbImage(MultipartFile multipartFile) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    /**
     * @param file 文件对象
     * @return 返回文件地址
     * @author qbanxiaoli
     * @description 上传文件
     */
    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
        return storePath.getFullPath();
    }

    /**
     * @param file 图片对象
     * @return 返回图片地址
     * @author qbanxiaoli
     * @description 上传缩略图
     */
    public String uploadImageAndCrtThumbImage(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
        return storePath.getFullPath();
    }

    /**
     * @param content       文件内容
     * @param fileExtension 文件后缀名
     * @return 返回文件地址
     * @author qbanxiaoli
     * @description 将一段字符串生成一个文件上传
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = fastFileStorageClient.uploadFile(stream, buff.length, fileExtension, null);
        return storePath.getFullPath();
    }

    /**
     * @param fileUrl 文件访问地址
     * @param file    文件保存路径
     * @author qbanxiaoli
     * @description 下载文件
     */
    public void downloadFile(String fileUrl, File file) {
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(bytes);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * @param fileUrl 文件访问地址
     * @author qbanxiaoli
     * @description 删除文件
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            log.error(e.getMessage());
        }
    }

    // 封装文件完整URL地址
    public String getResAccessUrl(String path) {
        String url = fdfsWebServer.getWebServerUrl() + path;
        log.info("上传文件地址为：\n" + url);
        return url;
    }

}