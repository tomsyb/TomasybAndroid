# Activity生命周期

本节主要有以下知识点

- Activity生命周期
- Actiivty之间的通信

用户交互的接口，通过Activity栈的形式来管理Activity，有Active/Paused/Stopped/Killed4种状态。

- Active:当前Acticity处于运行状态，获取了焦点
- Paused:暂停，失去焦点，未销毁，内存中的成员变量，状态信息等任然存在
- Stopped:Activity完全不可见，未销毁，内存中的成员变量，状态信息等任然存在
- Killed:销毁，信息被回收

## Activity的生命周期是怎么样的

1、Activity是通过栈的形式来管理的，主要情形如下:

- 正常启动onCreate-->onStart-->onResume
- 点击back回退，退出当前界面：onPause-onStop-onDestroy
- 按home返回桌面：onPause--onStop
- 再次回到界面:onRestart-onStart-onResume
- 打开新的界面:原来的界面onPause-->onStop 然后执行正常启动的方法
- 异常时调用onSaveInstanceState(Bundle ...)保存数据调用onRestoreInstanceState(Bundle ...) 恢复数据

2、特殊的生命周期

- 横竖屏切换

清单文件设置configChanges,值如下：
orientation:消除横竖屏的影响
keyboardHidden:消除键盘的影响
sceenSize:消除屏幕大小的影响

设置configChanges为orientation或者orientation|keyboardHidden或者不设置这个属性生命周期如下：

启动：onCreate-onStart-onResume  横竖屏切换 onPause-onSaveInstanceState-onStop-onDestroy-onCreate-onStart-onRestoreInstanceState-onResume

当我们设置Activity的android:configChanges属性为orientation|screenSize或者orientation|screenSize|keyboardHidden

   刚刚启动Activity的时候：

   onCreate
   onStart
   onResume

   由竖屏切换到横屏：

   什么也没有调用,调动onConfigurationChanged()不会调用Activity的生命周期

   由横屏切换到竖屏：

   什么也没有调用



## Actiivty之间的通信

- Activity与Activity之间的通信主要通过Intent/Bundle
- Activity与fragment通信，fragment.setArguments(Bundle ...)进行通信fragment调用getArguments()获取数据，以及接口
- fragment与fragment通信onActivityCreated中通过getActivity获取fragment的控件



