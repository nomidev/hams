package com.huneth.hams.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 시킨다.
// 로그인 진행이 완료가 되면 Security session을 만들어준다. (SecurityContextHolder)
// Object => Authentication
// Authentication 안에 User 정보가 있어야 됨.
// User의 타입은 UserDetails 타입

// Security Session <= Authentication <= UserDetails

import com.huneth.hams.model.User;
import com.huneth.hams.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<UserRole> userRole = user.getUserRoles();

        for (UserRole item : userRole) {
            collect.add(new SimpleGrantedAuthority(item.getRole().getRoleCode().toString()));
        }

        /*collect.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return null;
            }
        });*/
        // collect.add(new SimpleGrantedAuthority(user.getRole().name()));

        return collect;
    }

    public int getId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    public String getEmail() {
        return user.getEmail();
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

    // 계정 활성화
    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
