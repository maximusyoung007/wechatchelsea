package com.maximus.wechatchelsea.service;

import com.maximus.wechatchelsea.model.WeChatAccessToken;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 调用微信接口access_token 服务类
 * </p>
 *
 * @author ywj
 * @since 2021-06-07
 */
public interface WeChatAccessTokenService extends IService<WeChatAccessToken> {
    int saveAccessToken(String accessToken);
}
