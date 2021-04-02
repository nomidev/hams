package com.huneth.hams.board.controller;

import javax.validation.Valid;

import com.huneth.hams.common.config.validator.BoardValidator;
import com.huneth.hams.board.model.Board;
import com.huneth.hams.admin.model.Bulletin;
import com.huneth.hams.member.model.User;
import com.huneth.hams.board.repository.BoardRepository;
import com.huneth.hams.admin.repository.BulletinRepository;
import com.huneth.hams.member.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
@Slf4j
public class BoardController {

    // Autowired 주입이 아닌 생성자 주입방식
    private BoardRepository boardRepository;
    private BoardValidator boardValidator;
    private BulletinRepository bulletinRepository;
    private UserRepository userRepository;

    @GetMapping("/list")
    public String boardList(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText,
                       @RequestParam(required = false, defaultValue = "0") int bulletin_id) {
        // Page<Board> boardList = boardRepository.findAll(PageRequest.of(0, 20)); // jpa page가 0부터 시작한다.
        // Page<Board> boardList = boardRepository.findAll(pageable); // jpa page가 0부터 시작한다.
        log.debug("bulletin_id : " + bulletin_id);

        // jpa 페이징을 사용한다.
        Bulletin bulletin = bulletinRepository.findById(bulletin_id).orElseThrow(() -> new IllegalArgumentException("No such date"));
        Page<Board> boardList = boardRepository.findByBulletinAndTitleContainingOrBulletinAndContentContaining(bulletin, searchText, bulletin, searchText, pageable); // jpa page가 0부터 시작한다.
        // boardList.getTotalElements();

        int startPage = Math.max(1, (boardList.getPageable().getPageNumber() + 1) - 2);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber() + 3);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);
        return "board/boardList";
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
        return "redirect:/board/list?bulletin_id" + bulletin_id;
    }

    // controller에서 exceptionHandler를 처리할 수도 있다.
    /*@ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    private void PagingTest() {
        // https://okky.kr/article/282819
        // 페이징 테스트 소스
        int _totalCount = 103; // 전체레코드
        int _recordList = 10; // 페이지에 표시할 레코드
        int _totalPage = _totalCount / _recordList;
        int _pageCount = 10; // 1~10, 11~20, 21~30
        int _curPage = 0; // 현재 페이지번호

        // int / int = int;
        // 나머지가 있을 경우 페이지 + 1을 해야 한다.
        if (_totalCount % _recordList > 0) {
            _totalPage++;
        }

        // 전페 페이지보다 크면 전페 페이지로 한다.
        if (_totalPage < _curPage) {
            _curPage = _totalPage;
        }

        // 페이징목록이 10개씩 보일경우;
        // 현재 페이지가 _pageCount와 같을 경우 _pageCount를 벗어나기 때문에 -1을 해줘야 한다.
        int _startPage = ((_curPage - 1) / _pageCount) * 1 + 1;

        // 1일 경우 11이기 때문에 1~10되기 위해 -1해줘야 한다.
        int _endPage = _curPage + _pageCount - 1;

        if (_endPage > _totalPage) {
            _endPage = _totalPage;
        }

        for (int i = _startPage; i <= _endPage; i++) {
            System.out.print(" " + i + " ");
        }
    }
}
