package com.example.travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
            .antMatchers("/resources/**","/cssjs/**", "/img/**").permitAll() // CSS와 이미지 폴더를 로그인 없이 허용
            .antMatchers("/", "/signin","/signup").permitAll() // 루트 경로 및 로그인 페이지를 로그인 없이 허용
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/signin")
            .defaultSuccessUrl("/")
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }
}
