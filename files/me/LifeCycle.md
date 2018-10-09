# Activity生命周期

本节主要有以下知识点

- Activity生命周期
- Actiivty之间的通信

用户交互的接口，通过Activity栈的形式来管理Activity，有Active/Paused/Stopped/Killed4种状态。

## Activity的生命周期是怎么样的

Activity是通过栈的形式来管理的，主要情形如下:

- 正常启动onCreate()创建-->onStart-->onResume
- 点击back回退：onPause-onStop-onDestroy
- 打开新的界面:原来的界面onPause-->onStop 然后执行正常启动的方法
- 异常时调用onSaveInstanceState(Bundle ...)保存数据调用onRestoreInstanceState(Bundle ...) 恢复数据



## Actiivty之间的通信

- Activity与Activity之间的通信主要通过Intent/Bundle
- Activity与fragment通信，fragment.setArguments(Bundle ...)进行通信fragment调用getArguments()获取数据
-



