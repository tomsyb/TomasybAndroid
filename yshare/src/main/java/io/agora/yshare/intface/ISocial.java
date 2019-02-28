package io.agora.yshare.intface;

import io.agora.yshare.callback.SocialLoginCallback;
import io.agora.yshare.callback.SocialShareCallback;
import io.agora.yshare.entities.ShareEntity;
import io.agora.yshare.entities.ThirdInfoEntity;

/**
 * Created by arvinljw on 17/11/24 16:06
 * Function：
 * Desc：
 */
public interface ISocial {
    void login(SocialLoginCallback callback);

    ThirdInfoEntity createThirdInfo();

    void share(SocialShareCallback callback, ShareEntity shareInfo);

    void onDestroy();
}
