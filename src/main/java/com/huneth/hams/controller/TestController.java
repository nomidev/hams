package com.huneth.hams.controller;

import java.util.List;
import java.util.function.Supplier;

import com.huneth.hams.model.RoleType;
import com.huneth.hams.model.Users;
import com.huneth.hams.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// RestController는 뷰리졸버를 거치지 않고 데이터로 내려옴
// html이 아닌 data를 리턴해주는 컨트롤러
@RestController
public class TestController {

    // RestController가 메모리에 올라올때 같이 올라온다.
    // 의존성 주입 DI
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<Users> usersList() {
        return userRepository.findAll();
    }

    public Page<Users> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable page) {

        Page<Users> paginUser = userRepository.findAll(page);

        return paginUser;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "삭제할 대상이 없습니다.";
        }

        return "삭제되었습니다.";
    }

    @Transactional // 함수 종료시 자동 커밋
    @PutMapping("/dummy/user/{id}") // 업데이트
    public Users updateUser(@PathVariable int id, @RequestBody Users requestUser) {
        // Json데이터로 요청하면 MessageConverter가 자바 오브젝트로 변경해준다.

        // 영속화
        Users user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패했습니다.");
        });

        // 영속 컨텍스트에 내용을 바꿨기 때문에 @Transactional을 할 경우 변경을 감지해서 update가 이루어 진다.(더티 체킹)
        //user.setEmailAddress(requestUser.getEmailAddress());
        //user.setPassword(requestUser.getPassword());
        user.setPhoneNumber(requestUser.getPhoneNumber());

//        save함수는 id를 전달하지 않으면 Insert를 해주세고
//        Id가 있으면 업데이트 있으면 insert
//        userRepository.save(user);

        // 더티 체킹
        return user;
    }

//    {id} 주소로 파라미터를 받을 수 있다.
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

        // 람다식으로 처리도 가능하다
        /*Users user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });*/

        Users user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당유저는 없습니다.");
            }
        });

        // 요청: 웹브라우저
        // user : java 객체
        // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리 같은??)
        // 스프링부트 = MessageConverter가 응답시 자동으로 작동
        // 만약 자바 오브젝트를 리턴하면 MessageConverter가 Jackson 라이브러리를 호출해서
        // 자바오브젝트를 json으로 변환해서 브라우저에게 던진다.
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
