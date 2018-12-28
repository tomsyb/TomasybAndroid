package com.example.tomasyb.tomasybandroid.ui.audio;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.dl7.player.audio.VoiceManager;
import com.example.tomasyb.tomasybandroid.R;

/**
 * 录音自定义button
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-12-27.16:55
 * @since JDK 1.8
 */

public class RecordVoiceButton extends Button implements View.OnClickListener{
    private Context mContenxt;
    private VoiceManager mVoiceManager;
    private recordVoiceListener mListener;
    private Dialog mVoiceDialog;

    /**
     * 设置录音监听回调
     * @param mListener
     */
    public void setmListener(recordVoiceListener mListener) {
        this.mListener = mListener;
    }

    public RecordVoiceButton(Context context) {
        super(context);
        inits();
    }

    public RecordVoiceButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContenxt = context;
        inits();
    }

    public RecordVoiceButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContenxt = context;
        inits();
    }

    private void inits() {
        mVoiceManager = VoiceManager.getInstance(mContenxt);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // 点击按钮之后弹出框
        startRecordDialog();
    }

    /**
     * 弹出录音框
     */
    private void startRecordDialog() {
        mVoiceDialog = new Dialog(getContext(), R.style.record_voice_dialog);
        mVoiceDialog.setContentView(R.layout.dialog_record_voice);
    }

    /**
     * 结束回调监听
     */
    public interface recordVoiceListener{
        void onFinishRecord(long length,String strLength,String filePath);
    }
}
