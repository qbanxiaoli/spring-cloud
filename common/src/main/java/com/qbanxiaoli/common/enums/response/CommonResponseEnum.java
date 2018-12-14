package com.qbanxiaoli.common.enums.response;

import com.qbanxiaoli.common.enums.response.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:30
 */
@Getter
@AllArgsConstructor
public enum CommonResponseEnum implements ResponseEnum {

    SUCCESS(true, 200, "SUCCESS"),//成功
    SUCCESS_VARIABLE(true, 200, "SUCCESS_VARIABLE"),//传入成功变量信息
    PARAMETER_ERROR(false, 300, "PARAMETER_ERROR"),//参数错误
    FAILURE(false, 400, "FAILURE"),//失败
    FAILURE_VARIABLE(false, 400, "FAILURE_VARIABLE"),//传入失败变量信息
    MAX_FILE_SIZE(false, 400, "MAX_FILE_SIZE"),//上传单个文件大小不能超过1M
    FDFS_CONNECT_FAILURE(false, 400, "FDFS_CONNECT_FAILURE");//连接文件服务器失败

    private Boolean result;
    private Integer code;
    private String message;

}
