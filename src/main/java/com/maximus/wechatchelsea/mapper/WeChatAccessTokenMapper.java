package com.maximus.wechatchelsea.mapper;

import com.maximus.wechatchelsea.model.WeChatAccessToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 调用微信接口access_token Mapper 接口
 * </p>
 *
 * @author ywj
 * @since 2021-06-07
 */
@Mapper
public interface WeChatAccessTokenMapper extends BaseMapper<WeChatAccessToken> {
    int saveAccessToken(@Param("accessToken") String accessToken);
}
