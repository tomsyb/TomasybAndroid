package io.agora.yshare.core.handler.generic;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import io.agora.yshare.R;
import io.agora.yshare.core.BiliShareConfiguration;
import io.agora.yshare.core.SocializeMedia;
import io.agora.yshare.core.error.ShareException;
import io.agora.yshare.core.handler.BaseShareHandler;
import io.agora.yshare.core.shareparam.BaseShareParam;
import io.agora.yshare.core.shareparam.ShareParamAudio;
import io.agora.yshare.core.shareparam.ShareParamImage;
import io.agora.yshare.core.shareparam.ShareParamText;
import io.agora.yshare.core.shareparam.ShareParamVideo;
import io.agora.yshare.core.shareparam.ShareParamWebPage;
import io.agora.yshare.util.BuildHelper;

/**
 * ShareParam，只读取content的内容。
 *
 * @author Jungly
 * @email jungly.ik@gmail.com
 * @since 2016/4/11
 */
public class CopyShareHandler extends BaseShareHandler {

    public CopyShareHandler(Activity context, BiliShareConfiguration configuration) {
        super(context, configuration);
    }

    @Override
    public void checkConfig() throws Exception {

    }

    @Override
    public void init() throws Exception {

    }

    @Override
    protected void shareText(ShareParamText params) throws ShareException {
        share(params);
    }

    @Override
    protected void shareImage(ShareParamImage params) throws ShareException {
        share(params);
    }

    @Override
    protected void shareWebPage(ShareParamWebPage params) throws ShareException {
        share(params);
    }

    @Override
    protected void shareAudio(ShareParamAudio params) throws ShareException {
        share(params);
    }

    @Override
    protected void shareVideo(ShareParamVideo params) throws ShareException {
        share(params);
    }

    private void share(BaseShareParam param) {
        Context context = getContext();
        if (context == null) {
            return;
        }

        String content = param.getContent();
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (BuildHelper.isApi11_HoneyCombOrLater()) {
            clip.setPrimaryClip(ClipData.newPlainText(null, content));
        } else {
            ((android.text.ClipboardManager) clip).setText(content);
        }
        Toast.makeText(context, R.string.bili_share_sdk_share_copy, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isDisposable() {
        return true;
    }

    @Override
    public SocializeMedia getShareMedia() {
        return SocializeMedia.COPY;
    }

}
