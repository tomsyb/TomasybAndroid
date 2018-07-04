package com.example.tomasyb.tomasybandroid.net;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-4.18:41
 * @since JDK 1.8
 */

public class BaseEnty<T> {
    private long responseTime;
    private String message;
    private int code;
    private T data;

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
