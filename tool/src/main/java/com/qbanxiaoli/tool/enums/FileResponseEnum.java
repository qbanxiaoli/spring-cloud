package com.qbanxiaoli.tool.enums;

import com.qbanxiaoli.common.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/11/1 12:27
 */
@Getter
@AllArgsConstructor
public enum FileResponseEnum implements ResponseEnum {

    FILE_CONTENT_TYPE_ERROR(false, 400, "FILE_CONTENT_TYPE_ERROR");//上传文件类型错误

    private Boolean result;
    private Integer code;
    private String message;

}
