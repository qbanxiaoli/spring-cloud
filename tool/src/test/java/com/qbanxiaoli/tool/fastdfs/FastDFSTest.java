package com.qbanxiaoli.tool.fastdfs;

import com.qbanxiaoli.common.bean.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/9/7 下午7:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FastDFSTest {

    @Test
    public void upload() {
        String fileUrl = this.getClass().getResource("/picture.jpg").getPath();
        File file = new File(fileUrl);
        String storePath = FastDFSClient.uploadFile(file);
        String filePath = FastDFSClient.getResAccessUrl(storePath);
        log.info("上传文件路径为：" + filePath);
    }

}