package com.huneth.hams.member.service;

import javax.transaction.Transactional;

import com.huneth.hams.member.model.Role;
import com.huneth.hams.member.model.User;
import com.huneth.hams.member.model.UserRole;
import com.huneth.hams.member.repository.RoleRepository;
import com.huneth.hams.member.repository.UserRepository;
import com.huneth.hams.member.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User save(User user) {
        // 회원가입이 잘 되지만 스프링 시큐리티를 사용할 수 없다.
        // 비밀번호가 암호화가 안되어 있기 때문!
        // bCryptPasswordEncoder로 암호화를 해준다.
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(encPassword);
        user.setEnabled(true);
        User saveUser = userRepository.save(user);
        
        // user.setRole(RoleType.ROLE_USER);
        // 기본 사용자 ROLE 부여
        // 기본권한을 가져온다.
        Role role = roleRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("No date found"));
        UserRole userRole = new UserRole();
        userRole.setUser(saveUser);
        userRole.setRole(role);

        userRoleRepository.save(userRole);

        return saveUser;
    }

}
