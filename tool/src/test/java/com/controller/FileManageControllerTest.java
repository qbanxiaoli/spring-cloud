package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 10:09 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileManageControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void uploadImage() throws Exception {
        String fileUrl = this.getClass().getResource("/picture.png").getPath();
        File file = new File(fileUrl);
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file", //文件名
                file.getName(), //originalName 相当于上传文件在客户机上的文件名
                ContentType.IMAGE_PNG.toString(), //文件类型
                new FileInputStream(file) //文件流
        );
        this.mockMvc.perform(multipart("/fastdfs_file/image/upload")
                .file(mockMultipartFile))
                .andExpect(status().isOk()).andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
    }

}
