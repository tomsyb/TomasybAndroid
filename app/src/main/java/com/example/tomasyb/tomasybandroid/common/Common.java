package com.example.tomasyb.tomasybandroid.common;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.tomasyb.baselib.util.glide.widget.ImageData;
import com.example.tomasyb.tomasybandroid.ui.main.entity.NineImgEty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * app公共方法
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-9.11:42
 * @since JDK 1.8
 */

public class Common {
    public static int getSize(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    public static List<NineImgEty> getImages() {
        List<NineImgEty> list = new ArrayList<>();

        List<ImageData> images = new ArrayList<>();
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=3040385967,1031044866&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img1.imgtn.bdimg.com/it/u=1832737924,144748431&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img.zcool.cn/community/01d6dd554b93f0000001bf72b4f6ec.jpg"));
        list.add(new NineImgEty("2张图片和1张GIF动态图", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif"));
        list.add(new NineImgEty("1张GIF动态图", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=2091366266,1524114981&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=1424970962,1243597989&fm=21&gp=0.jpg"));
        list.add(new NineImgEty("3张图片", images));

        images = new ArrayList<>();
        ImageData imageData = new ImageData("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529402445474&di=b5da3b2f6a466e618e1e32d4dd2bda4d&imgtype=0&src=http%3A%2F%2F2b.zol-img.com.cn%2Fproduct%2F133_500x2000%2F801%2Fce21ke76FRh4A.jpg");
        imageData.realWidth = 500;
        imageData.realHeight = 281;
        images.add(imageData);
        list.add(new NineImgEty("一个小猫咪", images));

        images = new ArrayList<>();
        imageData = new ImageData("http://pic.58pic.com/58pic/13/62/02/07d58PICcxz_1024.jpg");
        imageData.realWidth = 1024;
        imageData.realHeight = 529;
        images.add(imageData);
        list.add(new NineImgEty("画卷：天道酬勤", images));

        images = new ArrayList<>();
        imageData = new ImageData("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498629002766&di=6fac924b9c9bc0858074a5eb455e4bd8&imgtype=0&src=http%3A%2F%2Fimg1.3lian.com%2F2015%2Fw21%2F8%2Fd%2F85.jpg");
        imageData.realWidth = 450;
        imageData.realHeight = 798;
        images.add(imageData);
        list.add(new NineImgEty("又一个小猫咪", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://img15.3lian.com/2015/h1/308/d/199.jpg"));
        images.add(new ImageData("http://pic1.win4000.com/wallpaper/f/566a714a786cc.jpg"));
        images.add(new ImageData("http://img15.3lian.com/2015/h1/308/d/204.jpg"));
        images.add(new ImageData("http://imglf1.ph.126.net/DIe00tFQ1SZOdKUDG8AO1g==/2068278128970036688.jpg"));
        list.add(new NineImgEty("世界著名建筑", images));

        images = new ArrayList<>();
        imageData = new ImageData("http://wx4.sinaimg.cn/mw690/e59bcb0dly1fgqlue0vtvj20c83htx3d.jpg");
        imageData.realWidth = 440;
        imageData.realHeight = 4529;
        images.add(imageData);
        list.add(new NineImgEty("开发者头条的截图(440X4529)", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://img2.imgtn.bdimg.com/it/u=2850936076,2080165544&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=698582197,4250615262&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=3191365283,111438732&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=482494496,1350922497&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img4.imgtn.bdimg.com/it/u=2440866214,1867472386&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=3040385967,1031044866&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img1.imgtn.bdimg.com/it/u=1832737924,144748431&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=2091366266,1524114981&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=2091366266,1524114981&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=1424970962,1243597989&fm=21&gp=0.jpg"));
        list.add(new NineImgEty("11张图片", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://img2.imgtn.bdimg.com/it/u=2850936076,2080165544&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=698582197,4250615262&fm=206&gp=0.jpg"));
        list.add(new NineImgEty("3张图片", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://img.zcool.cn/community/01d6dd554b93f0000001bf72b4f6ec.jpg"));
        images.add(new ImageData("http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif"));
        list.add(new NineImgEty("2张GIF动态图", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://img2.imgtn.bdimg.com/it/u=2850936076,2080165544&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=698582197,4250615262&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=3191365283,111438732&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=482494496,1350922497&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img4.imgtn.bdimg.com/it/u=2440866214,1867472386&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=3040385967,1031044866&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img1.imgtn.bdimg.com/it/u=1832737924,144748431&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=2091366266,1524114981&fm=21&gp=0.jpg"));
        list.add(new NineImgEty("9张图片", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=698582197,4250615262&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=3191365283,111438732&fm=21&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=482494496,1350922497&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img4.imgtn.bdimg.com/it/u=2440866214,1867472386&fm=21&gp=0.jpg"));
        list.add(new NineImgEty("5张图片", images));

        images = new ArrayList<>();
        images.add(new ImageData("http://img2.imgtn.bdimg.com/it/u=2850936076,2080165544&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img3.imgtn.bdimg.com/it/u=698582197,4250615262&fm=206&gp=0.jpg"));
        images.add(new ImageData("http://img5.imgtn.bdimg.com/it/u=3191365283,111438732&fm=21&gp=0.jpg"));
        list.add(new NineImgEty("4张图片", images));

        return list;
    }
}
