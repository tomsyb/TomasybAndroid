package com.example.tomasyb.tomasybandroid.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.SPUtils;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.net.WxApi;
import com.example.tomasyb.tomasybandroid.ui.login.SocialUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import io.agora.yshare.SocialHelper;
import io.agora.yshare.WXHelperActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class WXEntryActivity extends WXHelperActivity {

    @Override
    protected SocialHelper getSocialHelper() {
        return SocialUtil.INSTANCE.socialHelper;
    }
}
