# Android8.0系统

8.0主要改变

- 通知栏
- 应用图标

注意几点

- 通知渠道
- 配置要求（即运用通知8.0的要求）

# **配置要求**

```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.notificationtest"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
}

必须targetSdkVersion高于或等于26才能使用通知渠道
```










