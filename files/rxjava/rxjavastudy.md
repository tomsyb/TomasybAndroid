# Rxjava专栏

## **目录**

| 说明 | 序号 | 备注 |
| ------------- |:-------------| :-----|
| github地址及配置| 1| ... |
| Rxjava学习资源| 2| ... |
| 概述| 3| ... |
| dfs| 1| ... |
| dfs| 1| ... |
| dfs| 1| ... |
| dfs| 1| ... |


### 1、github地址：

[Rxjava](https://github.com/ReactiveX/RxJava)
[RxAndroid](https://github.com/ReactiveX/RxAndroid)

- 配置

gradle:(版本请查看官网最新)

```
compile 'io.reactivex:rxjava:1.0.14'
compile 'io.reactivex:rxandroid:1.0.1'
```

### 2、更多学习地址：

- [手把手教你使用Rxjava2.0](https://www.jianshu.com/p/d149043d103a)
- [给初学者的RxJava2.0教程](https://www.jianshu.com/p/464fa025229e)
- [给Android开发者的RxJava详解](http://gank.io/post/560e15be2dca930e00da1083)


### 3、概述

异步，逻辑复杂依然保持极强的阅读性,使用观察者模式

Rxjava观察者模式

- Observable: 被观察者
- Observer: 观察者 接受Observable发送的数据
- subscribe:订阅，观察者与被观察者通过subscribe()进行订阅
- Subscriber:也是一种观察者，在2.0中它与Observer没有实质区别不同的是 Subscriber要与Flowable(也是一种被观察者)联合使用，
该部分内容是2.0新增的，后续文章再介绍。Obsesrver用于订阅Observable，而Subscriber用于订阅Flowable


### **Observable操作符**

| 说明 | 操作符 | 备注 |
| ------------- |:-------------| :-----|
| 创建| create()| ... |
| just(T...)| 将传入的参数依次发送出来| ... |
| from(T[]) / from(Iterable<? extends T>)| 将传入的数组或 Iterable 拆分成具体对象后，依次发送出来| ... |
| dfs| 1| ... |
| dfs| 1| ... |
| dfs| 1| ... |
| dfs| 1| ... |

