package com.example.tomasyb.tomasybandroid.ui.rxjava.entity;

import java.util.List;

/**
 * 监控
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-11.10:43
 * @since JDK 1.8
 */

public class Video {

    /**
     * groupName : 商业街
     * list : [{"id":"649","name":"商业街","image":"http://file.geeker.com
     * .cn/file/images/20161116/1479265700310.jpg","longitude":"103.41137","latitude":"30.19743",
     * "url":"http://171.221.215.16:83/SEM/Movies/syj/syj.m3u8","status":"1","groupName":"商业街"},
     * {"id":"2109","name":"商业街1","image":"","longitude":"","latitude":"",
     * "url":"http://171.221.215.16:83/SEM/Movies/1tcctd1/1tcctd1.m3u8","status":"1",
     * "groupName":"商业街"},{"id":"2110","name":"商业街2","image":"","longitude":"","latitude":"",
     * "url":"http://171.221.215.16:83/SEM/Movies/1tcctd1/1tcctd1.m3u8","status":"1",
     * "groupName":"商业街"}]
     */

    private String groupName;
    private List<ListBean> list;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 649
         * name : 商业街
         * image : http://file.geeker.com.cn/file/images/20161116/1479265700310.jpg
         * longitude : 103.41137
         * latitude : 30.19743
         * url : http://171.221.215.16:83/SEM/Movies/syj/syj.m3u8
         * status : 1
         * groupName : 商业街
         */

        private String id;
        private String name;
        private String image;
        private String longitude;
        private String latitude;
        private String url;
        private String status;
        private String groupName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }
}
