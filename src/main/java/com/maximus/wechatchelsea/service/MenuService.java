package com.maximus.wechatchelsea.service;

import com.maximus.wechatchelsea.model.Menu;
import net.sf.json.JSONObject;

public interface MenuService {
    JSONObject getMenu(String accessToken);

    int createMenu(Menu menu, String accessToken);

    int deleteMenu(String accessToken);
}
