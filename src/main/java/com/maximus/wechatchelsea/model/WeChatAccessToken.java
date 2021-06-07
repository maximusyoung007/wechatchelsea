package com.maximus.wechatchelsea.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 调用微信接口access_token
 * </p>
 *
 * @author ywj
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatAccessToken implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * access_token
     */
    private String accessToken;

    /**
     * 新增时间
     */
    private LocalDateTime insertTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
