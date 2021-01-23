package com.huneth.hams.controller;

import com.huneth.hams.model.RoleType;
import com.huneth.hams.model.Users;
import com.huneth.hams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

// RestController는 뷰리졸버를 거치지 않고 데이터로 내려옴
@RestController
public class TestController {

    // RestController가 메모리에 올라올때 같이 올라온다.
    // 의존성 주입 DI
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dummy/user/{id}")
    public Users detail(@PathVariable int id) {
//        optional로 리턴을 한다. 조회했을때 null이 올수도 있기 때문에 null인지 판단해서 리턴해야 한다.
//        Users user = userRepository.findById(id).get(); 절대 null이 없다고 판단되면 바로 get으로 뽑아온다
        /*null일 경우 객체를 만들어서 리턴해야 한다.
        Users user = userRepository.findById(id).orElseGet(new Supplier<Users>() {
            @Override
            public Users get() {
                return new Users();
            }
        });*/
        Users user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당유저는 없습니다.");
            }
        });

        return user;
    }

    @PostMapping("/dummy/join")
    public String signUp(Users users) {

        if (users.getRole() == null) {
            users.setRole(RoleType.USER);
        }

        userRepository.save(users);
        return "회원가입이 완료되었습니다.";
    }
}
