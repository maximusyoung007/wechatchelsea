package com.maximus.wechatchelsea.util;

import com.maximus.wechatchelsea.model.TextMessage;
import com.maximus.wechatchelsea.util.MessageUtil;

import java.util.Date;
import java.util.Map;

public class MessageDispatcher {
    public static String processMessage(Map<String, String> map) {
        String openid = map.get("FromUserName"); //用户 openid
        String mpid = map.get("ToUserName");   //公众号原始 ID
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            //普通文本消息
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            txtmsg.setContent("欢迎来到我的公众号");
            return MessageUtil.textMessageToXml(txtmsg);
        }
        return null;
    }
    public String processEvent(Map<String, String> map) {
        //在这里处理事件
        return null;
    }
}
