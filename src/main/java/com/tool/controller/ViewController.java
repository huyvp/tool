package com.tool.controller;

import com.tool.dto.UserReq;
import com.tool.service.tab.TabService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ViewController {
    TabService tabService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/", "/index", "/home"})
    public String defaultView(Model model) {
        model.addAttribute("tabs", tabService.getTabs());
        return "/home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserReq user = new UserReq();
        model.addAttribute("user", user);
        return "/register";
    }
}
