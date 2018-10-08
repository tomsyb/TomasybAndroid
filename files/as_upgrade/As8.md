# Android8.0系统

参考文章

[Android通知栏微技巧，8.0系统中通知栏的适配郭林著](https://blog.csdn.net/guolin_blog/article/details/79854070)


8.0主要改变

- 通知栏
- 应用图标

8.0通过通知渠道来控制通知的类型，App可以创建不同的通知渠道，比如支付宝收益和美食推荐这两种渠道，用户可以自主选择关心该App的哪些通知(用户只需通过通知设置爱即可)


注意几点

- 通知渠道
- 配置要求（即运用通知8.0的要求）

# **配置要求**

```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26  （必须targetSdkVersion高于或等于26才能使用通知渠道，26以上不适配通知的话通知都收不到的窝）
    defaultConfig {
        applicationId "com.example.notificationtest"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
}



```
## **通知渠道的创建**

通知渠道通过一下几点来创建

- 渠道ID
- 渠道名称
- 重要等级














