package com.maximus.wechatchelsea.model;

import lombok.Data;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Data
public class AccessToken {
    private String access_token;

    private Integer expires_in;
}
