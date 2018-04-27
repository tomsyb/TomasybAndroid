# TomasybAndroid
我的Android开发

我们从
Application中开始封装,我们建类BaseApplication
实现IApp接口

## **一、BaseApplication**
IApp接口就是提供getAppComponent();


类中我们采用AppLifecycles接口代理Application的生命周期（3种）

- attachBaseContext
- onCreate
- onTerminate





