package com.example.tomasyb.baselib.net.entity;

/**
 * 服务器返回数据基类
 * 这里我们统一对数据基类处理
 * 如：
 *{
 "responseTime":1530755365407,
 "message":"success",
 "code":0,
 "data":Object{...}
 }
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-2.17:09
 * @since JDK 1.8
 */

public class BaseResponse<T> {
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
