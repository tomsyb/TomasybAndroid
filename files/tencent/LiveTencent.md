# 腾讯直播

目录

| 要点 | 序号 | 备注 |
| ------------- |:-------------| :-----|
| AS集成| 1| ... |

## 1、Android studio集成方案(ARR方式)

[下载sdk](https://cloud.tencent.com/document/product/454/7873)

这里有demo和sdk

配置前AS要求

以下是 SDK 的开发环境，APP 开发环境不需要与 SDK 一致，但要保证兼容：

- Android NDK: android-ndk-r12b
- Android SDK Tools: android-sdk_25.0.2
- minSdkVersion: 15
- targetSdkVersion: 21
- Android Studio（推荐您也使用 Android Studio，当然您也可以使用 Eclipse+ADT）

开始配置

- 1、导入arr如图

![导入arr](https://img-blog.csdn.net/20180614110656209?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RvbWFzeWI=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

- 2、在工程 app 目录下的 build.gradle 中，添加引用 aar 包的代码：

```
dependencies {
  compile fileTree(dir: 'libs', include: ['*.jar'])
  // 导入腾讯云直播 SDK aar
  compile(name: 'LiteAVSDK_Professional_3.0.1185', ext: 'aar')
}

//在工程目录下的 build.gradle 中，添加 flatDir，指定本地仓库：
allprojects {
  repositories {
      jcenter()
      flatDir {
          dirs 'libs'
      }
  }
}
//在工程目录下的 build.gradle 的 defaultConfig 里面，指定 ndk 兼容的架构：
defaultConfig {
    applicationId "com.tencent.liteav.demo"
    minSdkVersion rootProject.ext.minSdkVersion
    targetSdkVersion rootProject.ext.targetSdkVersion
    versionCode 1
    versionName "2.0"

    ndk {
        abiFilters "armeabi", "armeabi-v7a"
        // 如果您使用的是商业版，只能使用 armeabi 架构，即：
        // abiFilters "armeabi",
    }
}
最后编译一下工程 Rebuild Project。
```

- 3、 配置 APP 权限

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.CALL_PHONE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_LOGS" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-feature android:name="android.hardware.Camera"/>
<uses-feature android:name="android.hardware.camera.autofocus" />

```

- 4、验证

```
在 MainActivity.java 中引用 SDK 的 class：
import com.tencent.rtmp.TXLiveBase;

在 onCreate 中调用 getSDKVersioin 接口获取版本号：

String sdkver = TXLiveBase.getSDKVersionStr();
Log.d("liteavsdk", "liteav sdk version is : " + sdkver);
```
看日志输出即可验证成功与否

