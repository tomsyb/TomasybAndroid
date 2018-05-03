# 2018

# **面试指导文章**

[［干货］2017已来，最全面试总结——这些Android面试题你一定需要](https://blog.csdn.net/xhmj12/article/details/54730883)
## **一、Activity的生命周期**

![先看图](https://upload-images.jianshu.io/upload_images/1467278-3a28d45b96ce5745.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/664)

其实Activity中的方法大都是两两对应的，只有onRestart方法散发着单身狗的清香。
那么相邻的方法之间有什么区别呢？
![先看图](https://upload-images.jianshu.io/upload_images/1467278-21c8544f417e6713.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/550)

[界面实例](https://github.com/Tomsyb/TomasybAndroid/blob/master/app/src/main/java/com/example/tomasyb/tomasybandroid/example/life/ActivityLifeActivity.java)


## **二、Service生命周期**

两种启动方式：(两种生命周期不一样)

[查看图像](https://upload-images.jianshu.io/upload_images/1187237-4cbfd0f464cd5313.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/389)

- startService()
    生命周期：startService()->onCreate()->onStartConmon()->onDestroy()
    注意：
        1、多次startService()的时候onCreate只调用一次，onStartConmon()调用多次
        2、stopService()的时候onDestroy()调用从而销毁服务
        3.启动服务的时候通过Intent传值，在onStartConmont获取值的时候一定要先判断intent是否为空

- bindService()
    生命周期：bindService()->onCreate()->onBind()->unBind()->onDestroy()
    好处：更加便利activity操作service
    比如加入service中有几个方法，a,b ，如果要在activity中调用，在需要在activity获取ServiceConnection对象，
    通过ServiceConnection来获取service中内部类的类对象，然后通过这个类对象就可以调用类中的方法，当然这个类需要继承Binder对象

## **三、如何理解Activity，View，Window三者之间的关系？**

这个问题真的很不好回答。所以这里先来个算是比较恰当的比喻来形容下它们的关系吧。Activity像一个工匠（控制单元），Window像窗户（承载模型），View像窗花（显示视图）LayoutInflater像剪刀，Xml配置像窗花图纸。
1：Activity构造的时候会初始化一个Window，准确的说是PhoneWindow。
2：这个PhoneWindow有一个“ViewRoot”，这个“ViewRoot”是一个View或者说ViewGroup，是最初始的根视图。
3：“ViewRoot”通过addView方法来一个个的添加View。比如TextView，Button等
4：这些View的事件监听，是由WindowManagerService来接受消息，并且回调Activity函数。比如onClickListener，onKeyDown等。

## **四、Activity的几种LaunchMode及使用场景**

1.standard
默认
每次激活Activity会创建其实例，并放入任务栈中，
使用场景：大多数Activity
2.singleTop
栈顶单列
栈顶有就复用，调用onNewIntent() 让其回到栈顶
栈顶没有就创建实例放入栈顶，即使栈中有
3.singleTask
栈单列
栈中有就复用至栈顶，它上面的将移除栈，没有就创建实例放入栈顶
4.singleInstance
新栈单列
新建栈放实例，多实例共享该栈实例


## **五、View的绘制流程**


