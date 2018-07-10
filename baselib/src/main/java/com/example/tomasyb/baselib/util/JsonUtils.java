package com.example.tomasyb.baselib.util;

import com.google.gson.Gson;

/**
 * Json相关工具
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-9.11:45
 * @since JDK 1.8
 */

public class JsonUtils {
    /**
     * 将字符串转换为 对象
     * @param json
     * @param type
     * @return
     */
    public  static <T> T JsonToObject(String json, Class<T> type) {
        Gson gson =new Gson();
        return gson.fromJson(json, type);
    }
}
