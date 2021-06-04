package com.maximus.wechatchelsea.controller;

import com.maximus.wechatchelsea.util.MessageDispatcher;
import com.maximus.wechatchelsea.util.MessageUtil;
import com.maximus.wechatchelsea.util.WeiXinSignUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WXController {
    private static String token = "weixin3";

    @GetMapping("access")
    public String WeChatInterface(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("-----------------验证微信服务号信息开始---------------");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String ts = WeiXinSignUtil.getSHA1(token, timestamp, nonce);
        if (ts.equals(signature)) {
            System.out.println("微信服务号验证成功");
            return echostr;
        } else {
            System.out.println("不是微信发过来的请求");
            return null;
        }
    }

    @PostMapping("access")
    public String getWeiXinMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("----------开始接受微信服务号消息------------");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> parseXml = MessageUtil.parseXml(request);
        System.out.println(parseXml);
        String result = MessageDispatcher.processMessage(parseXml);
        System.out.println(result);
        return result;
    }
}
