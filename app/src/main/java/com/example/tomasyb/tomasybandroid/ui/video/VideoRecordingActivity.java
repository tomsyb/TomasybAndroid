package com.example.tomasyb.tomasybandroid.ui.video;

import android.Manifest;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.baselib.rx.permission.Permission;
import com.example.tomasyb.baselib.rx.permission.RxPermissions;
import com.example.tomasyb.tomasybandroid.R;

import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import io.reactivex.functions.Consumer;

/**
 * 视屏录制
 */
public class VideoRecordingActivity extends AppCompatActivity {
    public static final int RECORD_SYSTEM_VIDEO = 1;
    private VideoView mVideoView;        //用来显示播放录制后的视频
    private Button mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recording);
        final RxPermissions permissions = new RxPermissions(VideoRecordingActivity.this);
        permissions.setLogging(true);
        mVideoView = (VideoView) findViewById(R.id.videoView);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissions
                        .request(Manifest.permission.CAMERA,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    startActivity(new Intent(VideoRecordingActivity.this,IjkFullScreenActivity.class));
                                    // 用户已经同意该权限
                                    //reconverIntent();
                                }else {
                                    // 用户拒绝了该权限，并且选中『不再询问』
                                }
                            }
                        });
            }
        });
    }

    /**
     * 启用系统相机录制
     *
     */
    public void reconverIntent() {
        Uri fileUri = Uri.fromFile(getOutputMediaFile());
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);     //限制的录制时长 以秒为单位
//        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024);        //限制视频文件大小 以字节为单位
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);      //设置拍摄的质量0~1
//        intent.putExtra(MediaStore.EXTRA_FULL_SCREEN, false);        // 全屏设置
        startActivityForResult(intent, RECORD_SYSTEM_VIDEO);

    }

    /**
     * Create a File for saving an video
     */
    private File getOutputMediaFile() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(this, "请检查SDCard！", Toast.LENGTH_SHORT).show();
            return null;
        }

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment
                .DIRECTORY_DCIM), "MyCameraApp");
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp
                + ".mp4");
        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case RECORD_SYSTEM_VIDEO:
                mVideoView.setVideoURI(data.getData());
                mVideoView.start();
                break;

        }
    }


}
