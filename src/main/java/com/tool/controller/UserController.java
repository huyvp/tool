package com.tool.controller;

import com.tool.dto.UserReq;
import com.tool.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping(value = "users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping(value = "/create")
    public RedirectView createUser(@ModelAttribute UserReq userReq, HttpServletRequest request) {
        String remoteIp = request.getRemoteAddr();
        userService.createUser(userReq, remoteIp);
        return new RedirectView("/");
    }
}
