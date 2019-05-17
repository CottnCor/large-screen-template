package com.largeScreen.api.entity;

import java.io.Serializable;

public class HttpResult implements Serializable {

    private Integer code;

    private String body;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
