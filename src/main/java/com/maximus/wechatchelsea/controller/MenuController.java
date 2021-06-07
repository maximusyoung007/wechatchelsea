package com.maximus.wechatchelsea.controller;

import com.maximus.wechatchelsea.model.*;
import com.maximus.wechatchelsea.service.MenuService;
import com.maximus.wechatchelsea.service.WeChatAccessTokenService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @Resource
    private WeChatAccessTokenService tokenService;

    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    //查询全部菜单
    @GetMapping("get")
    public String getMenu() {
        List<WeChatAccessToken> tokenList = tokenService.list();
        String accessToken = tokenList.get(0).getAccessToken();
        JSONObject jsonObject = null;
        if (accessToken != null) {
            jsonObject = menuService.getMenu(accessToken);
            return String.valueOf(jsonObject);
        }
        logger.info("token为" + accessToken);
        return "无数据";
    }

    //创建菜单
    @PostMapping("create")
    public int createMenu() {
        String accessToken = tokenService.list().get(0).getAccessToken();
        int result = 0;
        if (accessToken != null) {
            result = menuService.createMenu(buildMenu(), accessToken);
        }
        if (result == 0) {
            logger.info("菜单创建成功! ");
        } else {
            logger.info("菜单创建失败，错误码：" + result);
        }
        return result;
    }

    //删除菜单
    @PostMapping("delete")
    public int deleteMenu() {
        String accessToken = tokenService.list().get(0).getAccessToken();
        int result = 0;
        if (accessToken != null) {
            result = menuService.deleteMenu(accessToken);
            // 判断菜单删除结果
            if (0 == result) {
                logger.info("菜单删除成功！");
            } else {
                logger.info("菜单删除失败，错误码：" + result);
            }
        }
        return result;
    }

    /**
     * 构建菜单
     * @return
     */
    private static Menu buildMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("个人信息");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("支部信息");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("党费缴纳");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("材料报送");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn15 = new CommonButton();
        btn15.setName("入党详情");
        btn15.setType("click");
        btn15.setKey("15");

        CommonButton btn22 = new CommonButton();
        btn22.setName("会议活动");
        btn22.setType("click");
        btn22.setKey("22");

        //view事件类型不需要key值
        CommonButton btn23 = new CommonButton();
        btn23.setName("百度搜索");
        btn23.setType("view");
//        btn23.setKey("23");
        btn23.setUrl("http://www.baidu.com");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("智慧党建0");
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14, btn15 });

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("智慧党建1");
        mainBtn2.setSub_button(new CommonButton[] { btn22});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("智慧党建2");
        mainBtn3.setSub_button(new CommonButton[] { btn23 });

        /**
         * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
         *
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是"更多体验"，而直接是"幽默笑话"，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;

    }
}
