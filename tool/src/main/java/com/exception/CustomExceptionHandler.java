package com.exception;

import com.github.tobato.fastdfs.exception.FdfsException;
import com.enums.response.CommonResponseEnum;
import com.model.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 *
 * @author qbanxiaoli
 * @description
 * @create 2018/12/14 1:20 AM
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(FdfsException.class)
    public ResponseVO handleFdfsException(FdfsException e) {
        log.error("连接文件服务器失败：" + e.getMessage());
        return new ResponseVO<>(CommonResponseEnum.FDFS_CONNECT_FAILURE);
    }

}
