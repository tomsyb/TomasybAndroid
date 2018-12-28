package com.example.tomasyb.tomasybandroid.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.agora.yshare.ShareUtils;
import io.agora.yshare.ThreadManager;
import io.agora.yview.dialog.BottomDialog;
import io.agora.yview.dialog.Item;
import io.agora.yview.dialog.OnItemClickListener;

public class ShareLoginMainActivity extends AppCompatActivity {
    private int mExtarFlag = 0x00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_login_main);
        ShareUtils.init(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_share)
    public void onViewClicked() {
        new BottomDialog(ShareLoginMainActivity.this)
                .title("提示")
                .orientation(BottomDialog.HORIZONTAL)
                .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                    @Override
                    public void click(Item item) {
                        Bundle params = new Bundle();
                        params.putString(QQShare.SHARE_TO_QQ_TITLE,"重庆直报分享地址");
                        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,"我是简介");
                        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/");
                        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,"重庆直报");
                        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, mExtarFlag);
                        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://inews.gtimg.com/newsapp_bt/0/876781763/1000");
                        doShareToQQ(params);
                    }
                })
                .show();

    }

    private void doShareToQQ(final Bundle params) {
        // QQ分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {

            @Override
            public void run() {
                if (null != ShareUtils.mTencent) {
                    ShareUtils.mTencent.shareToQQ(ShareLoginMainActivity.this, params, qqShareListener);
                }
            }
        });
    }
    IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
            if (QQShare.SHARE_TO_QQ_TYPE_DEFAULT != QQShare.SHARE_TO_QQ_TYPE_IMAGE) {
            }
        }
        @Override
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onError(UiError e) {
            // TODO Auto-generated method stub
        }
    };
}
