package com.maximus.wechatchelsea.scheduledTask;

import com.maximus.wechatchelsea.constants.WeiXinConstants;
import com.maximus.wechatchelsea.model.AccessToken;
import com.maximus.wechatchelsea.util.HttpUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时获取accessToken
 */
@Component
public class AccessTokenTask {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenTask.class);

    private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //每隔一段时间获取accessToken并存储
    @Scheduled(cron = "*/300 * * * * ?")
    public void saveAccessToken() {
        String accessToken = getAccessToken(WeiXinConstants.APPID, WeiXinConstants.APPSECRET);
        logger.info(accessToken);
    }

    public static String getAccessToken(String appId, String appSecret) {
        AccessToken accessToken = new AccessToken();
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject jsonObject = HttpUtil.doGetStr(requestUrl);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken.getAccess_token();

    }
}
