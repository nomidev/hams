package com.huneth.hams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/conf")
public class ConferenceController {

    /**
     * 학술대회 목록화면
     * @return
     */
    @GetMapping("/list")
    public String ConferenceList() {
        return "conference/confList";
    }
}
