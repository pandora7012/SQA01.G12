package com.bank.bankingweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        session.invalidate();
        return "redirect:/index";
    }

}
