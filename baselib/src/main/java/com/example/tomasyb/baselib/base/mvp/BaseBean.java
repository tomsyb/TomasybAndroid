package com.example.tomasyb.baselib.base.mvp;


import java.util.List;

/**
 * TODO 请求结果基础bean；仅用于判断操作是否成功
 * @param <T>
 */
public class BaseBean<T> {
    private long responseTime;
    private String message;
    private int code;
    private T data;//数据是个object
    private List<T> datas;//数据是个集合

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

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
