package com.example.tomasyb.tomasybandroid.common;

import java.util.Collection;

/**
 * app公共方法
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-9.11:42
 * @since JDK 1.8
 */

public class Common {
    public static int getSize(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }


}
