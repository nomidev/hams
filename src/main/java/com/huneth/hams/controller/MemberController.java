package com.huneth.hams.controller;

import com.huneth.hams.model.RoleType;
import com.huneth.hams.model.User;
import com.huneth.hams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public String join() {
        return "/member/join";
    }

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public String joinForm(User user) {
        System.out.println(user);

        // 회원가입이 잘 되지만 스프링 시큐리티를 사용할 수 없다.
        // 비밀번호가 암호화가 안되어 있기 때문!
        // bCryptPasswordEncoder로 암호화를 해준다.
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        // 기본 사용자 ROLE 부여
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return "redirect:/login";
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
