package com.tool.controller;

import com.tool.service.tab.TabService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
    TabService tabService;
    @GetMapping(value = "")
    public String getChatPage(HttpServletRequest request, Model model) {
        model.addAttribute("tabs", tabService.getTabs());
        return "chat";
    }
}
