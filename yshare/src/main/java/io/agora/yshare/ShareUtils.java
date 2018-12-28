package io.agora.yshare;

import android.content.Context;

import com.tencent.tauth.Tencent;

/**
 * 分享工具类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-12-25.9:59
 * @since JDK 1.8
 */

public class ShareUtils {
    public static Tencent mTencent;

    public static void init(Context context){
        if (mTencent == null) {
            mTencent = Tencent.createInstance(ShareConstants.APP_ID, context);
        }
    }
}
