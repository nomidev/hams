package com.huneth.hams.config;

import com.huneth.hams.config.handler.LoginSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
                    .requestMatchers("/api/auth/**").authenticated() // 인증된 사람만 받을 수 있는 api
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/**").permitAll()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/login") // loginForm이 호출되면 스프링 시큐리티가 낚아채서 대신 로그인을 해준다.
                    .defaultSuccessUrl("/")
                    .successHandler(loginSuccessHandler())
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .permitAll()
            )
            .exceptionHandling(exception -> exception
                    .authenticationEntryPoint(loginUrlAuthenticationEntryPoint())
            );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
//                .requestMatchers("/css/**", "/js/**", "/images/**", "/plugin/**");
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                // Chrome DevTools 관련 경로 무시 추가
                .requestMatchers("/.well-known/**");
    }

    // 해당 메서드의 리턴되는 오브젝트를 IoC에 등록해준다.
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ajax 세션 만료
    @Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        LoginUrlAuthenticationEntryPoint entry = new LoginUrlAuthenticationEntryPoint("/login") {
            @Override
            public void commence(final HttpServletRequest request, final HttpServletResponse response,
                                 final AuthenticationException authException) throws IOException, ServletException {

                String ajaxHeader = request.getHeader("X-Requested-With");
                if (ajaxHeader != null && "XMLHttpRequest".equals(ajaxHeader)) {
                    //InsufficientAuthenticationException
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                } else {
                    super.commence(request, response, authException);
                }
            }
        };
        return entry;
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email_adress,password,enabled "
                        + "from users "
                        + "where email_address = ?")
                .authoritiesByUsernameQuery("select email,authority "
                        + "from authorities "
                        + "where email = ?");
    }*/

    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("nomigood@naver.com")
                        .password("1234")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/
}