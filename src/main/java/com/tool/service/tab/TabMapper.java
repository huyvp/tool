package com.tool.service.tab;

import com.tool.dto.TabReq;
import com.tool.entity.Tab;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface TabMapper {
    Tab toTabFromTabReq(TabReq tabReq);

    void updateTab(@MappingTarget Tab tab, TabReq tabReq);
}
