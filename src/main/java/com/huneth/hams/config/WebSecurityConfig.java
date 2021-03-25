package com.huneth.hams.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
                .antMatchers("/api/auth/**").authenticated() // 인증된 사람만 받을 수 있는 api
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/").permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login") // loginForm이 호출되면 스프링 시큐리티가 낚아채서 대신 로그인을 해준다.
                .defaultSuccessUrl("/")
                .and()
            .logout(); //csrf와 사용할 경우 무조건 post 요청을 해야한다.
    }

    // 해당 메서드의 리턴되는 오브젝트를 IoC에 등록해준다.
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
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