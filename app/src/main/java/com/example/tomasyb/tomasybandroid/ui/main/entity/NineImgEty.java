package com.example.tomasyb.tomasybandroid.ui.main.entity;

import com.example.tomasyb.baselib.util.glide.widget.ImageData;

import java.io.Serializable;
import java.util.List;

/**
 * 朋友圈实体类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-1-29.15:01
 * @since JDK 1.8
 */

public class NineImgEty implements Serializable{
    public String desc;
    public List<ImageData> imgs;

    public NineImgEty(String desc, List<ImageData> imgs) {
        this.desc = desc;
        this.imgs = imgs;
    }
}
