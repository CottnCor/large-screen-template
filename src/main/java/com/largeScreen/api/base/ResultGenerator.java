package com.largeScreen.api.base;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	private static final String DEFAULT_SUCCESS_STATUS = "OK";

	private static final String DEFAULT_SUCCESS_FAILURE = "FAILURE";

	public static Result genSuccessResult() {
		return new Result().setStatus(DEFAULT_SUCCESS_STATUS).setCode(ResultCode.SUCCESS.code()).setMessage(DEFAULT_SUCCESS_MESSAGE);
	}

	public static Result genSuccessResult(Object data) {
		return new Result().setStatus(DEFAULT_SUCCESS_STATUS).setCode(ResultCode.SUCCESS.code()).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
	}

	public static Result genFailResult(String message) {
		return new Result().setStatus(DEFAULT_SUCCESS_FAILURE).setCode(ResultCode.FAIL.code()).setMessage(message);
	}

	public static Result genFailResult(Integer code, String message) {
		return new Result().setStatus(DEFAULT_SUCCESS_FAILURE).setStatus(ResultStatus.FAILURE.toString()).setCode(code).setMessage(message);
	}
}
