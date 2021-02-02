package com.huneth.hams.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 시킨다.
// 로그인 진행이 완료가 되면 Security session을 만들어준다. (SecurityContextHolder)
// Object => Authentication
// Authentication 안에 User 정보가 있어야 됨.
// User의 타입은 UserDetails 타입

// Security Session <= Authentication <= UserDetails

import com.huneth.hams.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;

    // 콤포지션
    public PrincipalDetails(User user) {
        this.user = user;
    }

    /**
     * 해당 User의 권한을 리턴한다.
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        /*collect.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return null;
            }
        });*/
        collect.add(new SimpleGrantedAuthority(user.getRole().name()));

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
