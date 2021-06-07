package com.maximus.wechatchelsea.service.serviceImpl;

import com.maximus.wechatchelsea.model.Menu;
import com.maximus.wechatchelsea.service.MenuService;
import com.maximus.wechatchelsea.util.HttpUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    private static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";

    private static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    @Override
    public JSONObject getMenu(String accessToken) {
        String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
        //调用接口查询菜单
        JSONObject jsonObject = HttpUtil.doGetStr(url);
        return jsonObject;
    }

    @Override
    public int createMenu(Menu menu, String accessToken) {
        int result = 0;
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        String jsonMenu = JSONObject.fromObject(menu).toString();
        logger.info("jsonMenu:" + jsonMenu);
        JSONObject jsonObject = HttpUtil.doPostStr(url, jsonMenu);
        logger.info("jsonObject-------------------------------" + String.valueOf(jsonObject));
        if (null != jsonObject) {
            if (jsonObject.getInt("errcode") != 0) {
                result = jsonObject.getInt("errcode");
                logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                logger.error("****"+jsonMenu+"****");
            }
        }
        return result;
    }

    @Override
    public int deleteMenu(String accessToken) {
        int result = 0;

        String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpUtil.doPostStr(url, null);

        if (null != jsonObject) {
            if (jsonObject.getInt("errcode") != 0) {
                result = jsonObject.getInt("errcode");
                logger.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}
