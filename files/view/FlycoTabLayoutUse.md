# FlycoTablayout用法

[Github地址](https://github.com/H07000223/FlycoTabLayout)

## 属性详解

```
tl_indicator_color 	 color 	            设置显示器颜色
tl_indicator_height 	dimension 	    设置显示器高度0就消失
tl_indicator_width 	dimension 	        设置显示器固定宽度
tl_indicator_margin_left 	dimension 	设置显示器margin,当indicator_width大于0,无效
tl_indicator_margin_top 	dimension 	设置显示器margin,当indicator_width大于0,无效
tl_indicator_margin_right 	dimension 	设置显示器margin,当indicator_width大于0,无效
tl_indicator_margin_bottom 	dimension 	设置显示器margin,当indicator_width大于0,无效
tl_indicator_corner_radius 	dimension 	设置显示器圆角弧度
tl_indicator_gravity 	enum 	        设置显示器上方(TOP)还是下方(BOTTOM),只对常规显示器有用
tl_indicator_style 	enum 	            设置显示器为常规(NORMAL)或三角形(TRIANGLE)或背景色块(BLOCK)
tl_underline_color 	color 	            设置下划线颜色
tl_underline_height 	dimension 	    设置下划线高度
tl_underline_gravity 	enum 	        设置下划线上方(TOP)还是下方(BOTTOM)
tl_divider_color 	color 	            设置分割线颜色
tl_divider_width 	dimension 	        设置分割线宽度
tl_divider_padding 	dimension 	        设置分割线的paddingTop和paddingBottom
tl_tab_padding 	dimension 	            设置tab的paddingLeft和paddingRight
tl_tab_space_equal 	boolean 	        设置tab大小等分
tl_tab_width 	dimension 	            设置tab固定大小
tl_textsize 	dimension 	            设置字体大小
tl_textSelectColor 	color 	            设置字体选中颜色
tl_textUnselectColor 	color 	        设置字体未选中颜色
tl_textBold 	boolean 	            设置字体加粗
tl_iconWidth 	dimension 	            设置icon宽度(仅支持CommonTabLayout)
tl_iconHeight 	dimension 	            设置icon高度(仅支持CommonTabLayout)
tl_iconVisible 	boolean 	            设置icon是否可见(仅支持CommonTabLayout)
tl_iconGravity 	enum 	                设置icon显示位置,对应Gravity中常量值,左上右下(仅支持CommonTabLayout)
tl_iconMargin 	dimension 	            设置icon与文字间距(仅支持CommonTabLayout)
tl_indicator_anim_enable 	boolean 	设置显示器支持动画(only for CommonTabLayout)
tl_indicator_anim_duration 	integer 	设置显示器动画时间(only for CommonTabLayout)
tl_indicator_bounce_enable 	boolean 	设置显示器支持动画回弹效果(only for CommonTabLayout)
tl_indicator_width_equal_title 	boolean 设置显示器与标题一样长(only for SlidingTabLayout)
tl:tl_textAllCaps="true"
方法：
mTbLayout.showDot(1);                   设置具体位置红点
 mTbLayout.showMsg(2,4);                设置显示红字数量
mTbLayout.setMsgMargin(2,0,10);         设置红字padding位置

MsgView rtv_2_3 = tabLayout_2.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }                               改变提醒数量的背景样式一般是红色，可自己修改

设置指示器是圆点 tl_indicator_width可设置长度设置成长条行圆角
 <com.flyco.tablayout.SlidingTabLayout
                android:id="@+id/tl_6"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:background="#EC7263"
                tl:tl_indicator_corner_radius="2dp"
                tl:tl_indicator_height="4dp"
                tl:tl_indicator_width="4dp"/>

设置指示器是背景色块
  <com.flyco.tablayout.SlidingTabLayout
                android:id="@+id/tl_10"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:background="#222831"
                android:paddingLeft="5dp"
                android:paddingRight="5dp"
                tl:tl_indicator_color="#393E46"
                tl:tl_indicator_corner_radius="5dp"
                tl:tl_indicator_margin_left="2dp"
                tl:tl_indicator_margin_right="2dp"
                tl:tl_indicator_style="BLOCK"/>
```






