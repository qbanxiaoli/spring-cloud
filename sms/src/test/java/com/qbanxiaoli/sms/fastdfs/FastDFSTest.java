package com.qbanxiaoli.sms.fastdfs;

import com.qbanxiaoli.common.bean.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/9/7 下午7:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FastDFSTest {

    @Autowired
    private FastDFSClient fastDFSClient;

    @Test
    public void Upload() throws Exception {
        File file = new File("/Users/qbanxiaoli/Pictures/45E2B7F32F61EB3D972C40F88805372D.png");
        String str = fastDFSClient.uploadFile(file);
        fastDFSClient.getResAccessUrl(str);
        String str1 = fastDFSClient.uploadImageAndCrtThumbImage(file);
        fastDFSClient.getResAccessUrl(str1);
        String str2 = fastDFSClient.uploadFile("123456", "jpg");
        fastDFSClient.getResAccessUrl(str2);
    }

    @Test
    public void Delete() {
        fastDFSClient.deleteFile("group1/M00/00/00/CrodvFuaFDuAdV5EAAPqBnoWbEk304.png");
    }

}