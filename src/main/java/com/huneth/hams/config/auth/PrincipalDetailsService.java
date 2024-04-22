package com.huneth.hams.config.auth;

import com.huneth.hams.model.User;
import com.huneth.hams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService타입으로 IoC되어 있는 loadUserByUsername 메소드가 실행된다.
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Security Session(Authentication(UserDetails))
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(s);

        if (userEntity == null) {
            throw new UsernameNotFoundException(s);
        }
        // 해당 유저가 있으면 UserDetails를 반환한다.
        return new PrincipalDetails(userEntity);
    }
}
