package com.example.tomasyb.tomasybandroid.api;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-11.17:13
 * @since JDK 1.8
 */

public class ScreenShotTools {
    public static Bitmap takeScreenShot(Activity activity) {
        Bitmap bitmap = null;
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        bitmap = view.getDrawingCache();

        // 获取状态栏的高度
        Rect frame = new Rect();
        // 测量屏幕宽高
        view.getWindowVisibleDisplayFrame(frame);
        int stautsHeight = frame.top;
        Log.i("aaaa", "状态栏的高度为" + stautsHeight);
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // 根据坐标点和需要的款和高创建bitmap
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        return bitmap;
    }

    // 保存图片到Sd卡中
    private static boolean savePic(Bitmap bitmap, String strName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(strName);
            if (null != fos) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);

                fos.flush();
                fos.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressLint("SimpleDateFormat")
    public static boolean shotBitmap(Activity activity) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
        Date date = new Date(currentTime);
        String photoName = "sdcard/" + "Screenshot_" + format.format(date)
                + ".png";
        MediaStore.Images.Media.insertImage(activity.getContentResolver(),
                takeScreenShot(activity), "a", "afd");
//
//        activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri
//                .parse("file://" + Environment.getExternalStorageDirectory())));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//如果是4.4及以上版本
            Intent mediaScanIntent = new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.parse("file://" + Environment.getExternalStorageDirectory()); //out is your output file
            mediaScanIntent.setData(contentUri);
            activity.sendBroadcast(mediaScanIntent);
        } else {
            activity.sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://"
                            + Environment.getExternalStorageDirectory())));
        }
        return ScreenShotTools.savePic(takeScreenShot(activity), photoName);
    }
}
