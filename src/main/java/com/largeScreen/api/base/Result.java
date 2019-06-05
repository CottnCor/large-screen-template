package com.largeScreen.api.base;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 */
public class Result {

	private String status="OK";

    private int code;

    private String message;

    private Object data;

    public Result setCode(int code) {
        this.code = code;
        return this;
    }    

    public String getStatus() {
		return status;
	}

	public Result setStatus(String status) {
		this.status = status;
		return this;
	}

	public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
