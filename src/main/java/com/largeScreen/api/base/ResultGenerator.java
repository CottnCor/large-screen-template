package com.largeScreen.api.base;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_STATUS = "OK";

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    private static final String DEFAULT_FAILURE_MESSAGE = "FAILURE";

    private static final String DEFAULT_FORBIDDEN_MESSAGE = "FORBIDDEN";

    public static Result genForbiddenResult(Object data) {
        return new Result().setStatus(DEFAULT_FORBIDDEN_MESSAGE).setCode(ResultCode.FORBIDDEN.code()).setMessage(DEFAULT_FORBIDDEN_MESSAGE).setData(data);
    }

    public static Result genSuccessResult(Object data) {
        return new Result().setStatus(DEFAULT_SUCCESS_STATUS).setCode(ResultCode.SUCCESS.code()).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result().setStatus(DEFAULT_FAILURE_MESSAGE).setCode(ResultCode.FAIL.code()).setMessage(message);
    }

    public static Result genFailResult(Integer code, String message) {
        return new Result().setStatus(DEFAULT_FAILURE_MESSAGE).setStatus(ResultStatus.FAILURE.toString()).setCode(code).setMessage(message);
    }
}
