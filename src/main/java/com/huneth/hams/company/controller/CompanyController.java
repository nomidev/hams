package com.huneth.hams.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

    /**
     * 회사소개
     * @return
     */
    @GetMapping("/about")
    public String about() {
        return "company/about";
    }
}
