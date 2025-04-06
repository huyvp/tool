package com.tool.service.user;

import com.tool.dto.UserCreateRequest;
import com.tool.entity.User;

public interface UserService {
    User createUser(UserCreateRequest userCreateRequest, String ip);

    User getUser(String username);
}
