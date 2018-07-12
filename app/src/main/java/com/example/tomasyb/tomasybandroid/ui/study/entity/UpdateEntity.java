package com.example.tomasyb.tomasybandroid.ui.study.entity;

/**
 * 更新实体类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-11.15:39
 * @since JDK 1.8
 */

public class UpdateEntity {

    /**
     * AppUpdateInfo : 优化登录逻辑,处理已知bug

     * DownPath : http://app.daqsoft.com/downapp.aspx?apptype=1&AppCode=81383
     * IsUpdate : 0
     * UpdateTime : 2018-06-27 17:25
     * VersionCode : 2.0.0
     */

    private String AppUpdateInfo;
    private String DownPath;
    private int IsUpdate;
    private String UpdateTime;
    private String VersionCode;

    public String getAppUpdateInfo() {
        return AppUpdateInfo;
    }

    public void setAppUpdateInfo(String AppUpdateInfo) {
        this.AppUpdateInfo = AppUpdateInfo;
    }

    public String getDownPath() {
        return DownPath;
    }

    public void setDownPath(String DownPath) {
        this.DownPath = DownPath;
    }

    public int getIsUpdate() {
        return IsUpdate;
    }

    public void setIsUpdate(int IsUpdate) {
        this.IsUpdate = IsUpdate;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(String VersionCode) {
        this.VersionCode = VersionCode;
    }
}
