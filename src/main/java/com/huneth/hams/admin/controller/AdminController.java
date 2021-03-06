package com.huneth.hams.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value = {"/", ""})
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/calendar")
    public String calendar() {
        return "admin/calendar";
    }
}
