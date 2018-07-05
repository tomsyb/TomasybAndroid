# 最流行的网络请求框架Rxjava2+Retrofit完美封装

## 配置：

```
    compile "io.reactivex.rxjava2:rxjava:$rootProject.ext.rxjava2Version"
    compile "com.squareup.retrofit2:retrofit:$rootProject.ext.retrofit2Version"
    compile "com.squareup.retrofit2:converter-scalars:$rootProject.ext.retrofit2Version"
    compile "com.squareup.retrofit2:converter-gson:$rootProject.ext.retrofit2Version"
    compile "com.squareup.retrofit2:adapter-rxjava2:$rootProject.ext.retrofit2Version"
    compile 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
    compile "com.trello.rxlifecycle2:rxlifecycle:$rootProject.ext.rxlifecycle"
    //compile "com.trello.rxlifecycle2:rxlifecycle-android:$rootProject.ext.rxlifecycle"
    compile "com.trello.rxlifecycle2:rxlifecycle-components:$rootProject.ext.rxlifecycle"

    在工程build中统一管理版本添加如下



    ext {
        supportLibVersion = '25.1.0'
        butterknifeVersion = '8.5.1'
        rxjava2Version = '2.0.8'
        retrofit2Version = '2.2.0'
        rxlifecycle='2.1.0'
        gsonVersion = '2.8.0'
    }


```

## 封装步骤


- 服务器响应数据的基类BasicResponse
- 构建初始化Retrofit的工具类IdeaApi
- 通过GsonConverterFactory获取真实响应数据
- 封装DefaultObserver处理服务器响应
- 处理加载Loading
- 管理Retrofit生命周期
- 如何使用封装

### 1、构建服务器返回数据基类BaseResponse

样式见

### 2、构建RetrofitUtils

 * 构建Retrofit.Builder，并对Okhttp配置
 * 1、 日志log打印json数据
 * 2. 设置Http请求头。防止每次输入
 * 3. 为OkHttp配置缓存。（包括其最大生命和缓存过期时间）
 * 4. 如果采用https，可在此处理证书校验以及服务器校验。
 * 5. 为Retrofit添加GsonConverterFactory


### 3、通过GsonConverterFactory获取真实响应数据

我们只关心返回成功的数据其他我们通过重写GsonConverterFactory，GsonRequestBodyConverter，GsonResponseBodyConverter来处理其他错误信息

### 4、构建DefaultObserver处理服务器响应。






