# 一个简单易用的Mvp+RxJava2+Retrofit2的开发框架封装

## 一、BasePresenter Presenter基类

接口：

```
start():默认初始化
detach(): Activity关闭吧view对象志为空
addDisposable();将网络请求的每一个disposable添加进入CompositeDisposable，再退出时候一并注销
unDisposable();注销所有请求
```

## 二、BaseView

```
showLoadingDialog:显示加载框
dismissLoadingDialog关闭加载框
```














