package com.example.tomasyb.tomasybandroid.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 微信登录界面
 */
public class WXLoginActivity extends AppCompatActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;
    // 微信登录相关
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxlogin);
        ButterKnife.bind(this);
        initwx();
    }

    /**
     * 微信初始化
     */
    private void initwx() {
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID_WX,true);
        api.registerApp(Constant.APP_ID_WX);
    }

    /**
     * 点击进行登录
     */
    @OnClick(R.id.btn_wx_login)
    public void onViewClicked() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_微信登录";
        api.sendReq(req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0){
            String headUrl = data.getStringExtra("headUrl");
            tvShow.setText(headUrl);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
