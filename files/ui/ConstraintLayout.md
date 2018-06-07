# ConstraintLayout完全解析

参考[此篇文章](https://blog.csdn.net/lmj623565791/article/details/78011599)进行总结不断完善中...

可视化布局
AS3.0配置：app gradle添加

```
compile 'com.android.support.constraint:constraint-layout:1.0.2'
```

## 方法API

这里只展示具有代表性的其他以此类推
如

```
    layout_constraintRight_toLeftOf
    layout_constraintRight_toRightOf
    layout_constraintTop_toTopOf
    layout_constraintTop_toBottomOf
    layout_constraintBottom_toTopOf
    layout_constraintBottom_toBottomOf
    layout_constraintBaseline_toBaselineOf
```

| 属性 | 说明 | 备注 |
| ------------- |:-------------| :-----|
| layout_constraintLeft_toLeftOf| 该控件左侧跟哪个的左侧对齐| 值：parent(父布局) id（控件id） |
| layout_constraintDimensionRatio | 宽高比| 值：W,16:6（宽）H,16:6（高） |
| layout_constraintHorizontal_weight | 横向权重| 配合layout_constraintHorizontal_chainStyle使用 |
| layout_constraintHorizontal_chainStyle | 链样式| spread（默认） spread_inside,packed|
| layout_constraintHorizontal_bias|横向拉力控制 | 当空间上下左右全部parent约束，设置此为0.9:上下两侧间隙比例分别为90%与10%|
| layout_constraintVertical_bias|纵向拉力控制 | 同上|
| layout_constraintVertical_bias|纵向拉力控制 | 同上|

## 注意

- 布局中match_constraint是0 让布局用约束控制
- 当控件w!=0,或固定值，约束不会改变控件的尺寸，w=0才能用约束控制控件尺寸

## 关于均分

1.3个按钮均分父布局

width=0，约束父布局即可
layout_constraintHorizontal_chainStyle=spread（默认可不设置）

2.按比列均分父布局

w=0:
layout_constraintHorizontal_chainStyle=spread（默认可不设置）
app:layout_constraintHorizontal_weight分别设置2,1,1实现2:1:1均分父布局

3.其他

- 链式样式spread + 宽度非0
- spread + 宽度为0，且可以通过weight控制分配比例
- spread_inside + 宽度非0
- packed + 宽度非0

## Guideline辅助布局

android.support.constraint.Guideline
辅助线不会显示到屏幕

1.属性：

| 属性 | 说明 | 备注 |
| ------------- |:-------------| :-----|
| orientation| 方向| 值：vertical(垂直) horizontal(横向) |
| layout_constraintGuide_begin| 距离顶部| dp |
| ..._end| 距离结束| dp |
| ..._percent| 距离顶部百分比| 0.8距离顶部80% |

