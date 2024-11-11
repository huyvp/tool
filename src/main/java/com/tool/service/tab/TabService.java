package com.tool.service.tab;

import com.tool.dto.TabReq;
import com.tool.entity.Tab;

import java.util.List;

public interface TabService {
    Tab createTab(TabReq tabReq);

    List<Tab> getTabs();

    Tab updateTab(TabReq tabReq, String id);

    void deleteTab(String id);
}
