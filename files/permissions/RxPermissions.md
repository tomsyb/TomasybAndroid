# 使用RxPermissions（基于RxJava2）

[官网](https://github.com/tbruyelle/RxPermissions/tree/master)

Android 6.0 （API level 23）中，将权限分成了两类。一类是Install权限（称之为安装时权限），另一类是Runtime权限（称之为运行时权限）。

-Install权限是什么？

Install权限：安装时权限，顾名思义，是在安装app时，就赋予该app的权限，即安装后立即获取到的权限。normal和signature级别的权限都是安装时权限。赋予app normal和signature权限时，不会给用户提示界面，系统自动决定权限的赋予。对于signature权限，如果使用权限的app与声明权限的app的签名不一致，则系统拒绝赋予该signature权限。

- Runtime权限是什么？

Runtime权限：运行时权限，是指在app运行过程中，赋予app的权限。这个过程中，会显示明显的权限授予界面，让用户决定是否授予权限。如果app的targetSdkVersion是22（Lollipop MR1）及以下，dangerous权限是安装时权限，否则dangerous权限是运行时权限。
如果一个app的targetSdkVersion是23（或者23以上），那么该app所申请的所有dangerous权限都是运行时权限。如果运行在Android 6.0的环境中，该app在运行时必须主动申请这些dangerous权限（调用requestPermissions()），否则该app没有获取到dangerous权限。


```
requestEach就是一次请求再回调
request 就是全部权限加载之后一起回调

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(RxPermissionTestActivity.this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d(TAG, permission.name + " is denied.");
                        }
                    }
                });


    }

    如果点击『拒绝』，不选中『不再询问』，log为：
    D/RxPermissionTest: android.permission.ACCESS_FINE_LOCATION is denied. More info should be provided.

    如果点击『拒绝』，选中了『不再询问』，则log为：

    D/RxPermissionTest: android.permission.ACCESS_FINE_LOCATION is denied.

    如果点击『允许』，log为：
    D/RxPermissionTest: android.permission.ACCESS_FINE_LOCATION is granted.

```






