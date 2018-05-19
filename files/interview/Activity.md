# Activity相关

![Activity生命周期图](https://upload-images.jianshu.io/upload_images/1467278-3a28d45b96ce5745.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/664)

## 1、4种状态

running/paused/stopped/killed

## 2、常用场景

- 点击Home键回到主界面
(Activity不可见)-->onPause()-->onStop()

- 回到原Activity
onRestart()-->onStart()-->onResume()

- 退出当前Activity
onPause()-->onStop()-->onDestroy()

# 3、进程优先级

前台/可见/服务/后台/空

# 4、android任务栈

# 5、启动模式

- standard 不管task中有没有Activity实例，都重新创建
- singletop 栈顶单列 如果栈顶有实例直接复用，如果没有重新创建，注意如果栈中有实例且没有在栈顶也要重新创建
- singletask 栈单列，栈中有就复用到栈顶，把他以上的所有Activity移除销毁
- singleinstance Activity只有一个实例独享一个任务栈

# 6、scheme跳转协议

页面内跳转协议，通过自己定义的scheme协议可跳转app中各个页面
通过scheme协议服务器可定制化告诉App跳转哪个页面，可通过通知栏消息定制化跳转页面
，可通过H5页面跳转页面





