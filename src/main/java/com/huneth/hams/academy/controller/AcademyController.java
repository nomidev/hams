package com.huneth.hams.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/academy")
public class AcademyController {

    /**
     * 학회 목록화면
     * @return
     */
    @GetMapping("/list")
    public String academyList() {
        return "academy/academyList";
    }
}
