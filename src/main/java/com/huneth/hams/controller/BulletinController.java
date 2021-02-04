package com.huneth.hams.controller;

import com.huneth.hams.model.Bulletin;
import com.huneth.hams.model.User;
import com.huneth.hams.repository.BulletinRepository;
import com.huneth.hams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;

@Controller
@RequestMapping("/bulletin")
public class BulletinController {

    @Autowired
    private BulletinRepository bulletinRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
        // Page<bulletin> bulletinList = bulletinRepository.findAll(PageRequest.of(0, 20)); // jpa page가 0부터 시작한다.
        // Page<bulletin> bulletinList = bulletinRepository.findAll(pageable); // jpa page가 0부터 시작한다.
        Page<Bulletin> bulletinList = bulletinRepository.findByBoardTitle(searchText, pageable); // jpa page가 0부터 시작한다.
        // bulletinList.getTotalElements();

        int startPage = Math.max(1, bulletinList.getPageable().getPageNumber() - 2);
        int endPage = Math.min(bulletinList.getTotalPages(), bulletinList.getPageable().getPageNumber() + 2);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("bulletinList", bulletinList);
        return "bulletin/list";
    }

    @GetMapping("/form")
    public String bulletinForm(Model model, @RequestParam(required = false) Integer id) {
        if (id == null) {
            // id가 없을 경우 새로운 bulletin를 생성해 화면으로 전달한다.
            model.addAttribute("bulletin", new Bulletin());
        } else {
            Bulletin bulletin = bulletinRepository.findById(id).orElse(null);
            model.addAttribute("bulletin", bulletin);
        }
        return "bulletin/form";
    }

    @PostMapping("/form")
    public String bulletinSubmit(@Valid Bulletin bulletin, BindingResult bindingResult, Authentication authentication) {
//        bulletinValidator.validate(bulletin, bindingResult);

        if (bindingResult.hasErrors()) {
            return "bulletin/form";
        }

        if (authentication == null) {
            return "redirect:/login?needLogin";
        }

        // 인증정보로 사용자를 가져와 저장한다.
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        bulletin.setUser(user);
        bulletinRepository.save(bulletin);
        return "redirect:/bulletin/list";
    }
}
