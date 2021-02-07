package com.huneth.hams.controller;

import javax.validation.Valid;

import com.huneth.hams.model.User;
import com.huneth.hams.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @Autowired
    private UserService userService;

    /**
     * 로그인 화면
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    /**
     * 회원가입 화면
     */
    @GetMapping("/join")
    public String join(Model model, User user) {
        return "/member/join";
    }

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public String joinSave(@Valid User user, BindingResult bindingResult) {
        System.out.println(user);

        if (bindingResult.hasErrors()) {
            return "member/join";
        }

        userService.save(user);

        return "redirect:/login?success";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "user";
    }

    @GetMapping("/info")
    @ResponseBody
    @Secured("ROLE_USER") // 메소드 단위로 시큐리티 적용
    public String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @ResponseBody
    @GetMapping("/date")
    public String date() {
        return "개인정보";
    }
}
