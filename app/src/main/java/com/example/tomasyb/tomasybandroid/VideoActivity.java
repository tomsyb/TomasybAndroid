package com.example.tomasyb.tomasybandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import java.io.File;
import java.io.IOException;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;
public class VideoActivity extends AppCompatActivity {
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;
    private static final int AUDIO_REQUEST_CODE = 103;
    private static final int SHOW = 1;
    private static final int CANCEL = 2;
    private boolean isRecording;
    public static Context currentActivity;
    private MediaProjectionManager mediaProjectionManager;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW:
                    if (isRecording) {
                        Toast.makeText(VideoActivity.this, "录制已开始", Toast.LENGTH_SHORT).show();
                    } else {
                        startScreenCapture();
                        isRecording = true;
                    }
                    break;
                case CANCEL:
                    if (isRecording) {
                        mediaRecorder.stop();
                        mediaRecorder.reset();
                        mediaProjection.stop();
                        virtualDisplay.release();
                        isRecording = false;
                        insertVideoToMediaStore(getSaveDirectory() + videoName);
                        Toast.makeText(VideoActivity.this, "录制结束", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(VideoActivity.this, "没有开始录制", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    private MediaProjection mediaProjection;
    private MediaRecorder mediaRecorder;
    private VirtualDisplay virtualDisplay;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    /**
     * 屏幕的高度
     */
    private int screenHeight;
    /**
     * 屏幕的像素
     */
    private int screenDpi;
    private DisplayMetrics metrics;
    /**
     * 保存在相册视频的名字
     */
    private String videoName;
    private boolean isfirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isfirst){
                    startRecording();
                    isfirst = false;
                }else {
                    stopRecordin();
                }
            }
        });
        mediaRecorder = new MediaRecorder();
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        /**
         * 动态注册权限
         */
        if (ContextCompat.checkSelfPermission(VideoActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    STORAGE_REQUEST_CODE);
        }
        if (ContextCompat.checkSelfPermission(VideoActivity.this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO },
                    AUDIO_REQUEST_CODE);
        }
        currentActivity = this;
        //mUnityPlayer.requestFocus();
    }





    /**
     * unity调用的方法，需要用一个handler进行处理实现功能，直接无法实现。
     */
    public void stopRecordin() {
        mHandler.sendEmptyMessage(CANCEL);
    }
    /**
     * unity调用的方法，需要用一个handler进行处理实现功能，直接无法实现。
     */
    public void startRecording() {
        mHandler.sendEmptyMessage(SHOW);

    }
    private void startScreenCapture() {
        mediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
        Intent captureIntent = mediaProjectionManager.createScreenCaptureIntent();
        startActivityForResult(captureIntent, RECORD_REQUEST_CODE);
    }
    public void setConfig(int screenWidth, int screenHeight, int screenDpi) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.screenDpi = screenDpi;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECORD_REQUEST_CODE && resultCode == RESULT_OK) {
            mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data);
            setConfig(metrics.widthPixels, metrics.heightPixels, metrics.densityDpi);
            startRecordS();
            Toast.makeText(this, "开始录制", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean startRecordS() {
        initRecorder();
        createVirtualDisplay();
        mediaRecorder.start();
        return true;
    }
    private void createVirtualDisplay() {
        virtualDisplay = mediaProjection.createVirtualDisplay("MainScreen", screenWidth, screenHeight, screenDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, mediaRecorder.getSurface(), null, null);
    }

    private void initRecorder() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        videoName = System.currentTimeMillis() + ".mp4";
        mediaRecorder.setOutputFile(getSaveDirectory() + videoName);
        mediaRecorder.setVideoSize(screenWidth, screenHeight);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setVideoEncodingBitRate(5 * 1024 * 1024);
        mediaRecorder.setVideoFrameRate(30);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSaveDirectory() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String screenRecordPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "奇见" + "/";
            return screenRecordPath;
        } else {
            return null;
        }
    }
    public void insertVideoToMediaStore(String filePath) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, filePath);
        // video/*
        values.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
        getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
    }

}
