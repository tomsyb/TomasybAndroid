package yanb.sharelib;

import yanb.sharelib.callback.SocialLoginCallback;
import yanb.sharelib.callback.SocialShareCallback;
import yanb.sharelib.entities.ShareEntity;
import yanb.sharelib.entities.ThirdInfoEntity;

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
