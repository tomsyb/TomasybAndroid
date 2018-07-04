package com.example.tomasyb.tomasybandroid.bean;

import java.util.List;

/**
 * 用户信息
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-4.11:47
 * @since JDK 1.8
 */

public class LoginUser {

    /**
     * responseTime : 1530675994393
     * message : success
     * code : 0
     * data : {"scenicName":"石象湖","code":"510131","headImg":"http://file.geeker.com.cn/uploadfile/scrs/img/1524642626572/tp1.jpg","meetName":"","nickName":"","latitude":"30.193642","userId":619,"vcode":"d80f699c062c8662fad3df86024e246c","userKey":"","personName":"超级管理员","rolePermission":{"top":[{"id":15,"name":"数字景区","code":"szjq","level":1,"pid":0},{"id":16,"name":"视频监控","code":"spjk","level":1,"pid":0},{"id":17,"name":"景区上报","code":"jqsb","level":1,"pid":0},{"id":18,"name":"签到","code":"qd","level":1,"pid":0},{"id":19,"name":"投诉建议","code":"tsjy","level":1,"pid":0},{"id":21,"name":"信息发布","code":"xxfb","level":1,"pid":0},{"id":23,"name":"电子巡更","code":"dzxg","level":1,"pid":0}],"xxfb":[{"id":63,"name":"微博","code":"wb","level":2,"pid":21},{"id":65,"name":"客户端","code":"khd","level":2,"pid":21},{"id":64,"name":"LED","code":"led","level":2,"pid":21},{"id":66,"name":"微官网","code":"wgw","level":2,"pid":21},{"id":67,"name":"APP","code":"app","level":2,"pid":21},{"id":68,"name":"官网","code":"gw","level":2,"pid":21},{"id":69,"name":"资讯网","code":"zxw","level":2,"pid":21}],"jqtcwtb":[{"id":47,"name":"停车位按天填报","code":"tcwattb","level":3,"pid":43},{"id":48,"name":"停车位按小时填报","code":"tcwaxstb","level":3,"pid":43},{"id":49,"name":"停车位5小时统计","code":"tcw5tj","level":3,"pid":43}],"jqssd":[{"id":37,"name":"景区舒适度-折线","code":"jqssd-zx","level":3,"pid":26},{"id":38,"name":"景区舒适度-百分比","code":"jqssd-bfb","level":3,"pid":26}],"jqsb":[{"id":42,"name":"景区人数填报","code":"jqrstb","level":2,"pid":17},{"id":43,"name":"景区停车位填报","code":"jqtcwtb","level":2,"pid":17}],"jqssrs":[{"id":33,"name":"客流趋势","code":"klqs","level":3,"pid":25},{"id":29,"name":"实时人数","code":"ssrs","level":3,"pid":25},{"id":30,"name":"人数走势","code":"rszs","level":3,"pid":25},{"id":34,"name":"团队数趋势","code":"tdsqs","level":3,"pid":25},{"id":35,"name":"销售量","code":"xsl","level":3,"pid":25},{"id":36,"name":"收入预估","code":"sryg","level":3,"pid":25},{"id":32,"name":"客流来源地","code":"kllyd","level":3,"pid":25}],"szjq":[{"id":25,"name":"景区实时人数","code":"jqssrs","level":2,"pid":15},{"id":26,"name":"景区舒适度","code":"jqssd","level":2,"pid":15},{"id":27,"name":"停车场使用","code":"tccsy","level":2,"pid":15},{"id":28,"name":"门票统计","code":"mptj","level":2,"pid":15}],"jqrstb":[{"id":44,"name":"人数按天填报","code":"rsattb","level":3,"pid":42},{"id":45,"name":"人数按小时填报","code":"rsaxstb","level":3,"pid":42},{"id":46,"name":"人数最近5小时统计","code":"rs5tj","level":3,"pid":42}],"mptj":[{"id":39,"name":"按日门票统计","code":"armptj","level":3,"pid":28},{"id":40,"name":"按月门票统计","code":"aymptj","level":3,"pid":28},{"id":41,"name":"按年门票统计","code":"anmptj","level":3,"pid":28}]},"appUserType":"appUserType1","phone":"13908224049","closeTime":"17:33","name":"超级管理员","personId":"3022","position":"保安","id":"","department":"安保部","job":"","email":"admin@daqsoft.com","longitude":"103.428253"}
     */

    private long responseTime;
    private String message;
    private int code;
    private DataBean data;

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * scenicName : 石象湖
         * code : 510131
         * headImg : http://file.geeker.com.cn/uploadfile/scrs/img/1524642626572/tp1.jpg
         * meetName :
         * nickName :
         * latitude : 30.193642
         * userId : 619
         * vcode : d80f699c062c8662fad3df86024e246c
         * userKey :
         * personName : 超级管理员
         * rolePermission : {"top":[{"id":15,"name":"数字景区","code":"szjq","level":1,"pid":0},{"id":16,"name":"视频监控","code":"spjk","level":1,"pid":0},{"id":17,"name":"景区上报","code":"jqsb","level":1,"pid":0},{"id":18,"name":"签到","code":"qd","level":1,"pid":0},{"id":19,"name":"投诉建议","code":"tsjy","level":1,"pid":0},{"id":21,"name":"信息发布","code":"xxfb","level":1,"pid":0},{"id":23,"name":"电子巡更","code":"dzxg","level":1,"pid":0}],"xxfb":[{"id":63,"name":"微博","code":"wb","level":2,"pid":21},{"id":65,"name":"客户端","code":"khd","level":2,"pid":21},{"id":64,"name":"LED","code":"led","level":2,"pid":21},{"id":66,"name":"微官网","code":"wgw","level":2,"pid":21},{"id":67,"name":"APP","code":"app","level":2,"pid":21},{"id":68,"name":"官网","code":"gw","level":2,"pid":21},{"id":69,"name":"资讯网","code":"zxw","level":2,"pid":21}],"jqtcwtb":[{"id":47,"name":"停车位按天填报","code":"tcwattb","level":3,"pid":43},{"id":48,"name":"停车位按小时填报","code":"tcwaxstb","level":3,"pid":43},{"id":49,"name":"停车位5小时统计","code":"tcw5tj","level":3,"pid":43}],"jqssd":[{"id":37,"name":"景区舒适度-折线","code":"jqssd-zx","level":3,"pid":26},{"id":38,"name":"景区舒适度-百分比","code":"jqssd-bfb","level":3,"pid":26}],"jqsb":[{"id":42,"name":"景区人数填报","code":"jqrstb","level":2,"pid":17},{"id":43,"name":"景区停车位填报","code":"jqtcwtb","level":2,"pid":17}],"jqssrs":[{"id":33,"name":"客流趋势","code":"klqs","level":3,"pid":25},{"id":29,"name":"实时人数","code":"ssrs","level":3,"pid":25},{"id":30,"name":"人数走势","code":"rszs","level":3,"pid":25},{"id":34,"name":"团队数趋势","code":"tdsqs","level":3,"pid":25},{"id":35,"name":"销售量","code":"xsl","level":3,"pid":25},{"id":36,"name":"收入预估","code":"sryg","level":3,"pid":25},{"id":32,"name":"客流来源地","code":"kllyd","level":3,"pid":25}],"szjq":[{"id":25,"name":"景区实时人数","code":"jqssrs","level":2,"pid":15},{"id":26,"name":"景区舒适度","code":"jqssd","level":2,"pid":15},{"id":27,"name":"停车场使用","code":"tccsy","level":2,"pid":15},{"id":28,"name":"门票统计","code":"mptj","level":2,"pid":15}],"jqrstb":[{"id":44,"name":"人数按天填报","code":"rsattb","level":3,"pid":42},{"id":45,"name":"人数按小时填报","code":"rsaxstb","level":3,"pid":42},{"id":46,"name":"人数最近5小时统计","code":"rs5tj","level":3,"pid":42}],"mptj":[{"id":39,"name":"按日门票统计","code":"armptj","level":3,"pid":28},{"id":40,"name":"按月门票统计","code":"aymptj","level":3,"pid":28},{"id":41,"name":"按年门票统计","code":"anmptj","level":3,"pid":28}]}
         * appUserType : appUserType1
         * phone : 13908224049
         * closeTime : 17:33
         * name : 超级管理员
         * personId : 3022
         * position : 保安
         * id :
         * department : 安保部
         * job :
         * email : admin@daqsoft.com
         * longitude : 103.428253
         */

        private String scenicName;
        private String code;
        private String headImg;
        private String meetName;
        private String nickName;
        private String latitude;
        private int userId;
        private String vcode;
        private String userKey;
        private String personName;
        private RolePermissionBean rolePermission;
        private String appUserType;
        private String phone;
        private String closeTime;
        private String name;
        private String personId;
        private String position;
        private String id;
        private String department;
        private String job;
        private String email;
        private String longitude;

        public String getScenicName() {
            return scenicName;
        }

        public void setScenicName(String scenicName) {
            this.scenicName = scenicName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getMeetName() {
            return meetName;
        }

        public void setMeetName(String meetName) {
            this.meetName = meetName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getVcode() {
            return vcode;
        }

        public void setVcode(String vcode) {
            this.vcode = vcode;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public RolePermissionBean getRolePermission() {
            return rolePermission;
        }

        public void setRolePermission(RolePermissionBean rolePermission) {
            this.rolePermission = rolePermission;
        }

        public String getAppUserType() {
            return appUserType;
        }

        public void setAppUserType(String appUserType) {
            this.appUserType = appUserType;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public static class RolePermissionBean {
            private List<TopBean> top;
            private List<XxfbBean> xxfb;
            private List<JqtcwtbBean> jqtcwtb;
            private List<JqssdBean> jqssd;
            private List<JqsbBean> jqsb;
            private List<JqssrsBean> jqssrs;
            private List<SzjqBean> szjq;
            private List<JqrstbBean> jqrstb;
            private List<MptjBean> mptj;

            public List<TopBean> getTop() {
                return top;
            }

            public void setTop(List<TopBean> top) {
                this.top = top;
            }

            public List<XxfbBean> getXxfb() {
                return xxfb;
            }

            public void setXxfb(List<XxfbBean> xxfb) {
                this.xxfb = xxfb;
            }

            public List<JqtcwtbBean> getJqtcwtb() {
                return jqtcwtb;
            }

            public void setJqtcwtb(List<JqtcwtbBean> jqtcwtb) {
                this.jqtcwtb = jqtcwtb;
            }

            public List<JqssdBean> getJqssd() {
                return jqssd;
            }

            public void setJqssd(List<JqssdBean> jqssd) {
                this.jqssd = jqssd;
            }

            public List<JqsbBean> getJqsb() {
                return jqsb;
            }

            public void setJqsb(List<JqsbBean> jqsb) {
                this.jqsb = jqsb;
            }

            public List<JqssrsBean> getJqssrs() {
                return jqssrs;
            }

            public void setJqssrs(List<JqssrsBean> jqssrs) {
                this.jqssrs = jqssrs;
            }

            public List<SzjqBean> getSzjq() {
                return szjq;
            }

            public void setSzjq(List<SzjqBean> szjq) {
                this.szjq = szjq;
            }

            public List<JqrstbBean> getJqrstb() {
                return jqrstb;
            }

            public void setJqrstb(List<JqrstbBean> jqrstb) {
                this.jqrstb = jqrstb;
            }

            public List<MptjBean> getMptj() {
                return mptj;
            }

            public void setMptj(List<MptjBean> mptj) {
                this.mptj = mptj;
            }

            public static class TopBean {
                /**
                 * id : 15
                 * name : 数字景区
                 * code : szjq
                 * level : 1
                 * pid : 0
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }

            public static class XxfbBean {
                /**
                 * id : 63
                 * name : 微博
                 * code : wb
                 * level : 2
                 * pid : 21
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }

            public static class JqtcwtbBean {
                /**
                 * id : 47
                 * name : 停车位按天填报
                 * code : tcwattb
                 * level : 3
                 * pid : 43
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }

            public static class JqssdBean {
                /**
                 * id : 37
                 * name : 景区舒适度-折线
                 * code : jqssd-zx
                 * level : 3
                 * pid : 26
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }

            public static class JqsbBean {
                /**
                 * id : 42
                 * name : 景区人数填报
                 * code : jqrstb
                 * level : 2
                 * pid : 17
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }

            public static class JqssrsBean {
                /**
                 * id : 33
                 * name : 客流趋势
                 * code : klqs
                 * level : 3
                 * pid : 25
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }

            public static class SzjqBean {
                /**
                 * id : 25
                 * name : 景区实时人数
                 * code : jqssrs
                 * level : 2
                 * pid : 15
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }

            public static class JqrstbBean {
                /**
                 * id : 44
                 * name : 人数按天填报
                 * code : rsattb
                 * level : 3
                 * pid : 42
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }

            public static class MptjBean {
                /**
                 * id : 39
                 * name : 按日门票统计
                 * code : armptj
                 * level : 3
                 * pid : 28
                 */

                private int id;
                private String name;
                private String code;
                private int level;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }
        }
    }
}
