package com.huneth.hams.controller;

import com.huneth.hams.model.CommonCode;
import com.huneth.hams.service.CommonCodeService;
import com.huneth.hams.commonEnum.YnFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CommonCodeService commonCodeService;

    @GetMapping(value = {"/", ""})
    public String adminHome() {
        return "admin/adminHome";
    }

    @GetMapping("/calendar")
    public String calendar() {
        return "admin/calendar";
    }

    @RequestMapping("/commonCode")
    public String commonCode() {
        return "admin/commonCode";
    }

    @RequestMapping("/bulletin")
    public String bulletin(Model model) {
        CommonCode param = new CommonCode();
        param.setUseFlag(YnFlag.Y);
        param.setCodeType("BULLETIN");

        model.addAttribute("bulletinType", commonCodeService.retrieveCommonCode(param));
        return "admin/bulletin";
    }
}
