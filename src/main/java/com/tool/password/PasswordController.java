package com.tool.password;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller()
@RequestMapping(value = "/accounts")
public class PasswordController {
    private final PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @GetMapping
    public RedirectView getAllPassword() {
        return new RedirectView("/accounts/dev");
    }

    @GetMapping(value = {"/{environment}", "/"})
    public String getPassByEnvironment(@PathVariable String environment, Model model, HttpServletRequest req) {
        environment = environment == null ? "dev" : environment;
        List<PasswordDto> passwords = passwordService.getPassByEnvironment(environment);
        model.addAttribute("passwords", passwords);
        model.addAttribute("uri", req.getRequestURI());
        return "account";
    }

    @GetMapping(value = {"/link"})
    public String getLink(Model model, HttpServletRequest req) {
        return "link";
    }

    @PostMapping("/update")
    public RedirectView updatePassword(@RequestBody Password password, HttpServletRequest req) {
        if (password.getPassword().isEmpty()) throw new RuntimeException("Password is require");
        passwordService.updatePassword(password, req.getRemoteAddr());
        return new RedirectView("/accounts/dev");
    }

    @PostMapping("/create")
    public String addPassword(@RequestBody @Valid Password password, HttpServletRequest req) {
        passwordService.insertPassword(password, req.getRemoteAddr());
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deletePassword(@RequestBody Password password) {
        passwordService.deletePassword(password);
        return "redirect:/";
    }
}
