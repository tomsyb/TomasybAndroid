package io.agora.yview.textview;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import io.agora.yview.R;


/**
 * 自定义控件，长文本展开收起TextView
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-3-25.15:09
 * @since JDK 1.8
 *
 * 使用：
设置TextView可展示的宽度 ( 父控件宽度 - 左右margin - 左右padding）
int whidth = ScreenUtils.getScreenWidth() - SizeUtils.dp2px(16 * 2);
extv.initWidth(whidth);

extv.setMaxLines(3);
extv.setCloseText(bean.getSummary());
 */
public class ExpandTextView extends AppCompatTextView {

    /**
     * 原始内容文本
     */
    private String originText;
    /**
     * TextView可展示宽度
     */
    private int initWidth = 0;
    /**
     * TextView最大行数
     */
    private int mMaxLines = 3;
    /**
     * 收起的文案(颜色处理)
     */
    private SpannableString SPAN_CLOSE = null;
    /**
     * 展开的文案(颜色处理)
     */
    private SpannableString SPAN_EXPAND = null;
    private String TEXT_EXPAND = "  查看全部>";
    private String TEXT_CLOSE = "  <收起";

    public ExpandTextView(Context context) {
        super(context);
        initCloseEnd();
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCloseEnd();
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCloseEnd();
    }

    /**
     * 设置TextView可显示的最大行数
     * @param maxLines 最大行数
     */
    @Override
    public void setMaxLines(int maxLines) {
        this.mMaxLines = maxLines;
        super.setMaxLines(maxLines);
    }

    /**
     * 初始化TextView的可展示宽度
     * @param width
     */
    public void initWidth(int width){
        initWidth = width;
    }

    /**
     * 收起的文案(颜色处理)初始化
     */
    private void initCloseEnd(){
        String content = TEXT_EXPAND;
        SPAN_CLOSE = new SpannableString(content);
        ButtonSpan span = new ButtonSpan(getContext(), new OnClickListener(){
            @Override
            public void onClick(View v) {
                ExpandTextView.super.setMaxLines(Integer.MAX_VALUE);
                setExpandText(originText);
            }
        }, R.color.y_main);
        SPAN_CLOSE.setSpan(span, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    /**
     * 展开的文案(颜色处理)初始化
     */
    private void initExpandEnd(){
        String content = TEXT_CLOSE;
        SPAN_EXPAND = new SpannableString(content);
        ButtonSpan span = new ButtonSpan(getContext(), new OnClickListener(){
            @Override
            public void onClick(View v) {
                ExpandTextView.super.setMaxLines(mMaxLines);
                setCloseText(originText);
            }
        }, R.color.y_main);
        SPAN_EXPAND.setSpan(span, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    public void setCloseText(CharSequence text) {

        if(SPAN_CLOSE == null){
            initCloseEnd();
        }
        // true 不需要展开收起功能， false 需要展开收起功能
        boolean appendShowAll = false;
        originText = text.toString();

        // SDK >= 16 可以直接从xml属性获取最大行数
        int maxLines = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            maxLines = getMaxLines();
        } else{
            maxLines = mMaxLines;
        }
        String workingText = new StringBuilder(originText).toString();
        if (maxLines != -1) {
            Layout layout = createWorkingLayout(workingText);
            if (layout.getLineCount() > maxLines) {
                // 获取一行显示字符个数，然后截取字符串数,收起状态原始文本截取展示的部分
                workingText = originText.substring(0, layout.getLineEnd(maxLines - 1)).trim();
                String showText = originText.substring(0, layout.getLineEnd(maxLines - 1)).trim() + "..." + SPAN_CLOSE;
                Layout layout2 = createWorkingLayout(showText);
                // 对workingText进行-1截取，直到展示行数==最大行数，并且添加 SPAN_CLOSE 后刚好占满最后一行
                while (layout2.getLineCount() > maxLines) {
                    int lastSpace = workingText.length()-1;
                    if (lastSpace == -1) {
                        break;
                    }
                    workingText = workingText.substring(0, lastSpace);
                    layout2 = createWorkingLayout(workingText + "..." + SPAN_CLOSE);
                }
                appendShowAll = true;
                workingText = workingText + "...";
            }
        }

        setText(workingText);
        if (appendShowAll) {
            // 必须使用append，不能在上面使用+连接，否则spannable会无效
            append(SPAN_CLOSE);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public void setExpandText(String text) {
        if(SPAN_EXPAND == null){
            initExpandEnd();
        }
        Layout layout1 = createWorkingLayout(text);
        Layout layout2 = createWorkingLayout(text + TEXT_CLOSE);
        // 展示全部原始内容时 如果 TEXT_CLOSE 需要换行才能显示完整，则直接将TEXT_CLOSE展示在下一行
        if(layout2.getLineCount() > layout1.getLineCount()){
            setText(originText + "\n");
        }else{
            setText(originText);
        }
        append(SPAN_EXPAND);
        setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     * 返回textview的显示区域的layout，该textview的layout并不会显示出来，只是用其宽度来比较要显示的文字是否过长
     * @param workingText
     * @return
     */
    private Layout createWorkingLayout(String workingText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return new StaticLayout(workingText, getPaint(), initWidth - getPaddingLeft() - getPaddingRight(),
                    Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), false);
        } else{
            return new StaticLayout(workingText, getPaint(), initWidth - getPaddingLeft() - getPaddingRight(),
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        }
    }
}