# 从底部的弹窗


类：DialogFromBottom

使用方法：

```
        View dialogContent = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null, false);
        DialogFromBottom dialogFromBottom = new DialogFromBottom(this);
        dialogFromBottom.setContentView(dialogContent);

        dialogFromBottom.show();

        布局就是dialogContent，单类定制化

```








