package com.huneth.hams.controller;

import com.huneth.hams.model.Board;
import com.huneth.hams.model.Bulletin;
import com.huneth.hams.model.User;
import com.huneth.hams.repository.BoardRepository;
import com.huneth.hams.repository.BulletinRepository;
import com.huneth.hams.repository.UserRepository;
import com.huneth.hams.config.validator.BoardValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private BulletinRepository bulletinRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String boardList(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText,
                       @RequestParam(required = false, defaultValue = "0") int bulletin_id) {
        // Page<Board> boardList = boardRepository.findAll(PageRequest.of(0, 20)); // jpa page가 0부터 시작한다.
        // Page<Board> boardList = boardRepository.findAll(pageable); // jpa page가 0부터 시작한다.
        log.debug("bulletin_id : " + bulletin_id);

        Bulletin bulletin = bulletinRepository.findById(bulletin_id).orElseThrow(() -> new IllegalArgumentException("No such date"));
        Page<Board> boardList = boardRepository.findByBulletinAndTitleContainingOrBulletinAndContentContaining(bulletin, searchText, bulletin, searchText, pageable); // jpa page가 0부터 시작한다.
        // boardList.getTotalElements();

        int startPage = Math.max(1, boardList.getPageable().getPageNumber() - 2);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber() + 2);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    @GetMapping("/form")
    public String boardForm(Model model, @RequestParam(required = false) Integer id, HttpSession session) {
        log.debug(session + "");

        if (id == null) {
            // id가 없을 경우 새로운 board를 생성해 화면으로 전달한다.
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/boardForm";
    }

    @PostMapping("/form")
    public String boardSave(@Valid Board board, @RequestParam Integer bulletin_id, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/boardForm";
        }

        if (authentication == null) {
            return "redirect:/login?needLogin";
        }

        Bulletin bulletin = bulletinRepository.findById(bulletin_id).orElse(null);
        // 인증정보로 사용자를 가져와 저장한다.
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        board.setUser(user);
        board.setBulletin(bulletin);
        boardRepository.save(board);
        return "redirect:/board/boardList";
    }

    /*@ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
