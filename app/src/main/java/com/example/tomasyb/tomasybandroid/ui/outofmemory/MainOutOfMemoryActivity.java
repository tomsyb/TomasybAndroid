package com.example.tomasyb.tomasybandroid.ui.outofmemory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.tomasybandroid.R;

/**
 （1）内存溢出（OOM）和内存泄露（对象无法被回收）的区别。
 （2）引起内存泄露的原因
 (3) 内存泄露检测工具 ------>LeakCanary

 内存溢出 out of memory：是指程序在申请内存时，没有足够的内存空间供其使用，出现out of memory；比如申请了一个integer,但给它存了long才能存下的数，那就是内存溢出。内存溢出通俗的讲就是内存不够用。
 内存泄露 memory leak：是指程序在申请内存后，无法释放已申请的内存空间，一次内存泄露危害可以忽略，但内存泄露堆积后果很严重，无论多少内存,迟早会被占光
 内存泄露原因：
 一、Handler 引起的内存泄漏。
 解决：将Handler声明为静态内部类，就不会持有外部类SecondActivity的引用，其生命周期就和外部类无关，
 如果Handler里面需要context的话，可以通过弱引用方式引用外部类
 二、单例模式引起的内存泄漏。
 解决：Context是ApplicationContext，由于ApplicationContext的生命周期是和app一致的，不会导致内存泄漏
 三、非静态内部类创建静态实例引起的内存泄漏。
 解决：把内部类修改为静态的就可以避免内存泄漏了
 四、非静态匿名内部类引起的内存泄漏。
 解决：将匿名内部类设置为静态的。
 五、注册/反注册未成对使用引起的内存泄漏。
 注册广播接受器、EventBus等，记得解绑。
 六、资源对象没有关闭引起的内存泄漏。
 在这些资源不使用的时候，记得调用相应的类似close（）、destroy（）、recycler（）、release（）等方法释放。
 七、集合对象没有及时清理引起的内存泄漏。
 通常会把一些对象装入到集合中，当不使用的时候一定要记得及时清理集合，让相关对象不再被引用。
 */
public class MainOutOfMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outof_memory_main);
    }
}
