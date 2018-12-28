/*
 * Copyright (C) 2015 Bilibili <jungly.ik@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.agora.yshare.core.handler.wx;

import android.app.Activity;

import io.agora.yshare.core.BiliShareConfiguration;
import io.agora.yshare.core.SocializeMedia;
import io.agora.yshare.core.error.ShareException;
import io.agora.yshare.core.shareparam.ShareParamImage;
import io.agora.yshare.core.shareparam.ShareParamWebPage;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

/**
 * @author Jungly
 * @email jungly.ik@gmail.com
 * @since 2015/9/31 18:44
 */
public class WxMomentShareHandler extends BaseWxShareHandler {

    public WxMomentShareHandler(Activity context, BiliShareConfiguration configuration) {
        super(context, configuration);
    }

    @Override
    protected void shareImage(final ShareParamImage params) throws ShareException {
        if (params.getImage() != null && (!params.getImage().isUnknowImage())) {
            super.shareImage(params);
        } else {
            ShareParamWebPage webpage = new ShareParamWebPage(params.getTitle(), params.getContent(), params.getTargetUrl());
            webpage.setThumb(params.getImage());
            shareWebPage(webpage);
        }
    }

    @Override
    int getShareType() {
        return SendMessageToWX.Req.WXSceneTimeline;
    }

    @Override
    public SocializeMedia getShareMedia() {
        return SocializeMedia.WEIXIN_MONMENT;
    }
}

