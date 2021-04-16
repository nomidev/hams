package com.huneth.hams.admin.controller;

import com.huneth.hams.admin.model.CommonCode;
import com.huneth.hams.admin.service.CommonCodeService;
import com.huneth.hams.common.commonEnum.YnFlag;
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
        return "admin/home";
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
