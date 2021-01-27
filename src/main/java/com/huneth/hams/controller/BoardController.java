package com.huneth.hams.controller;

import com.huneth.hams.model.Board;
import com.huneth.hams.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

//    글씨로 화면
    @GetMapping("/form")
    public String form(Model model) {
        return "board/form";
    }

    @PostMapping("/form")
    public String saveForm() {
        return "redirect:board/list";
    }
}
