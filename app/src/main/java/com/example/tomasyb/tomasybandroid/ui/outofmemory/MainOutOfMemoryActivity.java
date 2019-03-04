package com.example.tomasyb.tomasybandroid.ui.outofmemory;

import android.os.Handler;
import android.os.Message;
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

 内存分配策略
 内存分配策略：1.静态、栈式和堆试分配（对应的内存空间静态存储区也叫方法区、栈区、堆区）
 静态存储区：放静态数据，全局static数据和常量，此内存在编译时就已经分配好，在程序整个运行期间都存在
 栈区：      当方法被执行时，方法体内的局部变量（其中包括基础数据类型、对象的引用）都在栈上创建，并在方法执行结束时这些局部变量所持有的内存将会自动被释放。因为栈内存分配运算内置于处理器的指令集中，效率很高，但是分配的内存容量有限
 堆区：       又称动态内存分配，通常就是指在程序运行时直接 new 出来的内存，也就是对象的实例。这部分内存在不使用时将会由 Java 垃圾回收器来负责回收



 */
public class MainOutOfMemoryActivity extends AppCompatActivity {
    /**
     * 作用:
     * 处理异步消息
     * 主线程和子线程通过Handler来进行通信，子线程可以通过Handler来通知主线程进行UI更新
     * 警告：In Android, Handler classes should be static or leaks might occur.会出现严重的内存泄漏
     * Handler分析：
     * android:程序启动，framework会
     *
     */
    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outof_memory_main);
    }

    /**
     * 总结：
     * 局部变量的基本数据类型和引用存储在栈中，引用的对象实体在堆中，因为它们属于方法中的变量，生命周期随方法结束
     * 成员变量全部存储在堆中（包括基本数据类型，引用和引用的对象实体）因它们属于类，类对象终究是要被new出来使用的
     */
    Sample sample3 = new Sample();//指向的对象在堆上，包括这个对象的所有成员s1和sample1
    public class Sample{
        int s1 = 0;
        Sample sample1 = new Sample();
        public void method(){
            //局部变量s2和引用变量sample2在栈中
            int s2=1;
            Sample sample2 = new Sample();//其指向的对象在堆上
        }
    }

}
