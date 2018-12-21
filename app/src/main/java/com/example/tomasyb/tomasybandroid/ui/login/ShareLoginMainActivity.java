package com.example.tomasyb.tomasybandroid.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.agora.yview.dialog.BottomDialog;
import io.agora.yview.dialog.Item;
import io.agora.yview.dialog.OnItemClickListener;

public class ShareLoginMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_login_main);
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
                        ToastUtils.showLong(item.getTitle());
                    }
                })
                .show();

    }
}
