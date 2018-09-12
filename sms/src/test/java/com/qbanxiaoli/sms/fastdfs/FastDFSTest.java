package com.qbanxiaoli.sms.fastdfs;

import com.qbanxiaoli.common.bean.FastDFSClient;
import com.qbanxiaoli.common.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 *
 *
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
    public void Test() throws Exception {
        File file = new File("/Users/qbanxiaoli/Pictures/45E2B7F32F61EB3D972C40F88805372D.jpg");
        String str = fastDFSClient.uploadFile(file);
        log.info(str);
    }

}