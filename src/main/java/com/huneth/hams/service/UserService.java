package com.huneth.hams.service;

import com.huneth.hams.model.RoleType;
import com.huneth.hams.model.User;
import com.huneth.hams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        // 회원가입이 잘 되지만 스프링 시큐리티를 사용할 수 없다.
        // 비밀번호가 암호화가 안되어 있기 때문!
        // bCryptPasswordEncoder로 암호화를 해준다.
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(encPassword);
        user.setEnabled(true);
        // 기본 사용자 ROLE 부여
        user.setRole(RoleType.ROLE_USER);
        userRepository.save(user);

        return userRepository.save(user);
    }
}
