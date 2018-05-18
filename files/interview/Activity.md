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





