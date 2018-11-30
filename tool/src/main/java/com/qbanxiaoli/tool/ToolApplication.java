package com.qbanxiaoli.tool;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.spring.annotation.MapperScan;

import java.security.Principal;

@SpringBootApplication
@EnableOAuth2Sso
@ComponentScan("com.qbanxiaoli")
@MapperScan("com.qbanxiaoli.tool.dao.mapper")
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class ToolApplication {

    @RequestMapping("/")
    public String home(Principal user) {
        return "Hello " + user.getName();
    }

    public static void main(String[] args) {
        SpringApplication.run(ToolApplication.class, args);
    }

}
