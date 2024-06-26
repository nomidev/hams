package com.huneth.hams.config;

import com.huneth.hams.config.auth.PrincipalDetails;
import groovy.util.logging.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
@Slf4j
public class SpringSecurityAuditorAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        /**
         * SecurityContext 에서 인증정보를 가져와 주입시킨다.
         * 현재 코드는 현재 Context 유저가 USER 인가 권한이 있으면, 해당 Principal name 을 대입하고, 아니면 Null 을 set 한다.
         */
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(authentication -> {
                    Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
                    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
                    // boolean isUser = auth.contains(new SimpleGrantedAuthority("USER"));
                    if (authentication.isAuthenticated()) return principalDetails.getId();
                    return null;
                });
    }
}
