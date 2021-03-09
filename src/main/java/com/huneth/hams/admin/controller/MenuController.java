package com.huneth.hams.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
public class MenuController {

    @GetMapping("/menu")
    public String menu() {
        return "admin/menu";
    }
}
