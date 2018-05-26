# Rxjava专栏

github地址：

[Rxjava](https://github.com/ReactiveX/RxJava)
[RxAndroid](https://github.com/ReactiveX/RxAndroid)

更多学习地址：

- [手把手教你使用Rxjava2.0](https://www.jianshu.com/p/d149043d103a)
- [给初学者的RxJava2.0教程](https://www.jianshu.com/p/464fa025229e)

# 配置

gradle:(版本请查看官网最新)

```
compile 'io.reactivex:rxjava:1.0.14'
compile 'io.reactivex:rxandroid:1.0.1'
```

异步，逻辑复杂依然保持极强的阅读性,使用观察者模式

# **重要概念**

- Observable: 被观察者
- Observer: 观察者 接受Observable发送的数据
- subscribe:订阅，观察者与被观察者通过subscribe()进行订阅
- Subscriber:也是一种观察者，在2.0中它与Observer没有实质区别不同的是 Subscriber要与Flowable(也是一种被观察者)联合使用，
该部分内容是2.0新增的，后续文章再介绍。Obsesrver用于订阅Observable，而Subscriber用于订阅Flowable

# **理解观察者模式**




