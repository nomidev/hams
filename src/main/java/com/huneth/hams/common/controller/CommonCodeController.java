package com.huneth.hams.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CommonCodeController {

    @RequestMapping("")
    public String cmmcList() {
        return "admin/cmmcList";
    }

    @GetMapping("/form")
    public String cmmcForm() {
        return "admin/cmmcForm";
    }

    @PostMapping("/form")
    public void cmmcSave() {

    }
}
