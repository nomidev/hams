package com.huneth.hams.config;

import com.huneth.hams.config.auth.PrincipalDetails;
import groovy.util.logging.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
@Slf4j
public class SpringSecurityAuditorAware implements AuditorAware<Integer> {

//    Spring Security와 JPA를 연동하여 **생성자(CreatedBy)**나 **수정자(LastModifiedBy)**를 자동으로 기록하기 위한 설정

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증 정보가 없거나, 인증되지 않은 사용자(Anonymous)인 경우 처리
        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }

        // 사용자의 ID(Integer)를 반환 (UserDetails 구현체에 따라 캐스팅 필요)
        // 예: UserPrincipal 클래스에 id 필드가 있는 경우
        // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        // return Optional.of(principal.getId());

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        return Optional.of(principalDetails.getId()); // 테스트용 고정값, 실제로는 위와 같이 principal에서 ID 추출

        /**
         * SecurityContext 에서 인증정보를 가져와 주입시킨다.
         * 현재 코드는 현재 Context 유저가 USER 인가 권한이 있으면, 해당 Principal name 을 대입하고, 아니면 Null 을 set 한다.
         */
        /*return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(authentication -> {
                    Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
                    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
                    // boolean isUser = auth.contains(new SimpleGrantedAuthority("USER"));
                    if (authentication.isAuthenticated()) return principalDetails.getId();
                    return null;
                });*/
    }
}
