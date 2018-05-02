# 2018

## **面试指导文章**

[［干货］2017已来，最全面试总结——这些Android面试题你一定需要](https://blog.csdn.net/xhmj12/article/details/54730883)
## **一、Activity的生命周期**

[先看图](https://upload-images.jianshu.io/upload_images/1467278-3a28d45b96ce5745.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/664)

onCreate()-->onStart()-->onResume()-->onPause()-->onStop()-->onDestroy()

## **Service生命周期**

两种启动方式：(两种生命周期不一样)

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







