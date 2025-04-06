package com.tool.service.user;

import com.tool.dto.UserCreateRequest;
import com.tool.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUserFromUserReq(UserCreateRequest userCreateRequest);
}
