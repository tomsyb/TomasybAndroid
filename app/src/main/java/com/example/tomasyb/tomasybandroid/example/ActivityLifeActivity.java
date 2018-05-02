package com.example.tomasyb.tomasybandroid.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.utilslib.utils.L;

/**
 * Activity生命周期
 * 知识点：
 * onCreate和onStart之间有什么区别？
 *      （1）可见与不可见的区别。前者不可见，后者可见。
 *      （2）执行次数的区别。onCreate方法只在Activity创建时执行一次，而onStart方法在Activity的切换以及按Home键返
 *              回桌面再切回应用的过程中被多次调用。因此Bundle数据的恢复在onStart中进行比onCreate中执行更合适。
 *      （3）onCreate能做的事onStart其实都能做，但是onstart能做的事onCreate却未必适合做。如前文所说的，
 *              setContentView和资源初始化在两者都能做，然而想动画的初始化在onStart中做比较好
 *
 *  onStart方法和onResume方法有什么区别？
 *  （1）是否在前台。onStart方法中Activity可见但不在前台，不可交互，而在onResume中在前台。
 *  （2）职责不同，onStart方法中主要还是进行初始化工作，而onResume方法，根据官方的建议，可以做开启动画和独占设备的操作。
 *
 *  onPause方法和onStop方法有什么区别？
 *  （1）是否可见。onPause时Activity可见，onStop时Activity不可见，但Activity对象还在内存中。
 *  （2）在系统内存不足的时候可能不会执行onStop方法，因此程序状态的保存、独占设备和动画的关闭、以及一些数据的保存最好在onPause中进行，但要注意不能太耗时
 *
 *  onStop方法和onDestroy方法有什么区别？
 *
 *  7.与生命周期密切相关的onSaveInstanceState方法和onRestoreInstanceState方法在什么时候执行？
 *  通过阅读源码会发现，当targetSdkVersion小于3时onSaveInstanceState是在onPause方法中调用的，而大于3时是在onStop方法中调用的。
 *  而onRestoreInstanceState是在onStart之后、onResume之前调用的。
 */
public class ActivityLifeActivity extends AppCompatActivity {

    /**
     * 创建 第一方法
     * Activity初始化工作
     * 1.加载布局
     * 2.变量初始化
     *
     * 处于后台不可见（动画不在这里因为不可见）
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        L.e("onCreate处于后台不可见");
    }

    /**
     * 处于可见，未出现在前台我们看不到
     */
    @Override
    protected void onStart() {
        super.onStart();
        L.e("onStart()处于可见，未出现在前台我们看不到");
    }

    /**
     * 前台，用户可见
     */
    @Override
    protected void onResume() {
        super.onResume();
        L.e("onResume()前台，用户可见");
    }

    /**
     * 暂停
     * Activity跳转或正常退出
     * 处于前台并可见
     * 可进行轻量级的存储数据和去初始化工作，不能太耗时（因为在跳Activity时只有当当前activity的onPause执行完才能
     * 另一个Activity才会启动，onPause在0.5秒没有执行完毕，会强制关闭Activity）
     */
    @Override
    protected void onPause() {
        super.onPause();
        L.e("onPause()暂停处于前台并可见");
    }

    /**
     * 停止
     * 不可见
     * Activity对象还在内存中没有被销毁
     * 做资源回收
     */
    @Override
    protected void onStop() {
        super.onStop();
        L.e("onStop()不可见");
    }

    /**
     * 销毁
     * Activity被销毁
     * 不可见
     * 释放资源，处理回收工作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e("onDestroy()销毁不可见");
    }

    /**
     * 重启
     * 可见
     * 触发机制：
     * 1.home键桌面和Activity切换
     * 2.ActivityB切回ActivityA
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        L.e("onRestart()重启可见");
    }
}
