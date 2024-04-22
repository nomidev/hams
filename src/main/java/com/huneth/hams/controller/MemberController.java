package com.huneth.hams.controller;

import com.huneth.hams.config.validator.UserValidator;
import com.huneth.hams.service.MailService;
import com.huneth.hams.dto.UserDto;
import com.huneth.hams.model.User;
import com.huneth.hams.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 로그인 화면
     * @return
     */
    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "member/login";
    }

    /**
     * 회원가입 화면
     */
    @GetMapping("/join")
    public String join(Model model, UserDto userDto) {
        return "member/join";
    }

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public String joinSave(@Valid UserDto userDto, BindingResult bindingResult) {
        System.out.println(userDto);

        // Custom 유효성 검사(비밀번호 확인 검사)
        userValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "member/join";
        }

        User user = modelMapper.map(userDto, User.class);

        userService.save(user);

        return "redirect:/login?success";
    }

    @GetMapping("/emailCheck")
    @ResponseBody
    public ResponseEntity<Object> emailCheck() {
        mailService.sendMail("nomigood@naver.com");
        return new ResponseEntity<>("전송완료", HttpStatus.OK);
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "user";
    }

    /*@GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "user";
    }*/

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
