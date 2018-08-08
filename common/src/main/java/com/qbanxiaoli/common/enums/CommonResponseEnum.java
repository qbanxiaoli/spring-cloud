package com.qbanxiaoli.common.enums;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:30
 */
public enum CommonResponseEnum implements ResponseEnum {

    SUCCESS(true, 200, "SUCCESS"),//成功
    SUCCESS_VARIABLE(true, 200, "SUCCESS_VARIABLE"),//传入成功变量信息
    PARAMETER_ERROR(false, 300, "PARAMETER_ERROR"),//参数错误
    FAILURE(false, 400, "FAILURE"),//失败
    FAILURE_VARIABLE(false, 400, "FAILURE_VARIABLE");//传入失败变量信息

    private Boolean result;
    private Integer code;
    private String message;

    @Override
    public Boolean getResult() {
        return result;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CommonResponseEnum(Boolean result, Integer code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

}
