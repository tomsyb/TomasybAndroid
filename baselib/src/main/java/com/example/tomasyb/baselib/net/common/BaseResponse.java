package com.example.tomasyb.baselib.net.common;

/**
 * 服务器返回数据基类
 * 如：
 * {
 * "code": 200,
 * "message": "成功",
 * "content": {
 * ...
 * }
 * }
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-2.17:09
 * @since JDK 1.8
 */

public class BaseResponse<T> {
    private int code;
    private String message;
    private T results;
    private boolean error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
