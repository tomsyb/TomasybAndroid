package yanb.sharelib.callback;
import yanb.sharelib.entities.ThirdInfoEntity;

/**
 * Created by arvinljw on 17/11/24 15:46
 * Function：
 * Desc：
 */
public interface SocialLoginCallback extends SocialCallback {
    void loginSuccess(ThirdInfoEntity info);
}
