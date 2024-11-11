package com.tool.controller;

import com.tool.service.account.AccountService;
import com.tool.dto.AccountDelete;
import com.tool.dto.AccountReq;
import com.tool.dto.AccountResp;
import com.tool.dto.AccountUpdate;
import com.tool.service.tab.TabService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RequestMapping(value = "/accounts")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    AccountService accountService;
    TabService tabService;

    @GetMapping
    public RedirectView getAllPassword() {
        return new RedirectView("/accounts/common");
    }

    @GetMapping(value = {"/{environment}", "/"})
    public String getAccountByEnvironment(@PathVariable String environment, Model model, HttpServletRequest req) {
        environment = environment == null ? "common" : environment;
        AccountReq accountReq = new AccountReq();
        List<AccountResp> accounts = accountService.getAccountByEnvironment(environment);
        model.addAttribute("accounts", accounts);
        model.addAttribute("uri", req.getRequestURI());
        model.addAttribute("account", accountReq);
        model.addAttribute("tabs", tabService.getTabs());
        return "account";
    }

    @PostMapping("/update")
    public RedirectView updateAccount(@ModelAttribute @Valid AccountUpdate accountUpdate,
                                      HttpServletRequest req) {
        accountService.updateAccount(
                accountUpdate, req.getRemoteAddr()
        );
        return new RedirectView("/accounts/" + accountUpdate.getEnvironment());
    }

    @PostMapping("/create")
    public RedirectView createAccount(@ModelAttribute("account") @Valid AccountReq accountReq,
                                      HttpServletRequest req) {
        accountService.insertAccount(
                accountReq, req.getRemoteAddr()
        );
        return new RedirectView("/accounts/" + accountReq.getEnvironment());
    }

    @PostMapping("/delete")
    public RedirectView deleteAccount(@ModelAttribute @Valid AccountDelete accountDelete) {
        accountService.deleteAccount(accountDelete.getId());
        return new RedirectView("/accounts/" + accountDelete.getEnvironment());
    }

    @GetMapping(value = {"/link"})
    public String getLink() {
        return "link";
    }
}
