package com.tool.controller;

import com.tool.dto.UserCreateRequest;
import com.tool.entity.User;
import com.tool.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createUser(@ModelAttribute UserCreateRequest userCreateRequest,
                                     HttpServletRequest request) {
        log.info("User created: {}", userCreateRequest.toString());
        String remoteIp = request.getRemoteAddr();
        User data = userService.createUser(
                userCreateRequest, remoteIp
        );
        return ResponseEntity.ok(data);
    }
}
