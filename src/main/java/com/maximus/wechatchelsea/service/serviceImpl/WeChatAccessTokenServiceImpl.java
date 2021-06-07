package com.maximus.wechatchelsea.service.serviceImpl;

import com.maximus.wechatchelsea.model.WeChatAccessToken;
import com.maximus.wechatchelsea.mapper.WeChatAccessTokenMapper;
import com.maximus.wechatchelsea.service.WeChatAccessTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 调用微信接口access_token 服务实现类
 * </p>
 *
 * @author ywj
 * @since 2021-06-07
 */
@Service
public class WeChatAccessTokenServiceImpl extends ServiceImpl<WeChatAccessTokenMapper, WeChatAccessToken> implements WeChatAccessTokenService {
    @Resource
    private WeChatAccessTokenMapper tokenMapper;

    public int saveAccessToken(String accessToken) {
        return tokenMapper.saveAccessToken(accessToken);
    }
}
