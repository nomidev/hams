package com.huneth.hams.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
public class BoardController {

    @GetMapping("/list")
    public String retrieveList() {
        return "board/list";
    }
}
