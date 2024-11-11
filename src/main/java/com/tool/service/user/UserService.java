package com.tool.service.user;

import com.tool.dto.UserReq;
import com.tool.entity.User;

public interface UserService {
    void createUser(UserReq userReq, String ip);

    User getUser(String username);
}
