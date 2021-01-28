package com.huneth.hams.controller;

import com.huneth.hams.model.Board;
import com.huneth.hams.repository.BoardRepository;
import com.huneth.hams.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boardList = boardRepository.findAll(PageRequest.of(0, 20)); // jpa page가 0부터 시작한다.
//        Page<Board> boardList = boardRepository.findAll(pageable); // jpa page가 0부터 시작한다.
        Page<Board> boardList = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable); // jpa page가 0부터 시작한다.
//        boardList.getTotalElements();

        int startPage = Math.max(1, boardList.getPageable().getPageNumber() - 2);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber() + 2);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/form")
    public String boardForm(Model model, @RequestParam(required = false) Integer id) {
        if (id == null) {
            // id가 없을 경우 새로운 board를 생성해 화면으로 전달한다.
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String boardSubmit(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
