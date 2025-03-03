package com.example.package404.config;

import com.example.package404.config.filter.JwtFilter;
import com.example.package404.config.filter.LoginFilter;
import com.example.package404.user.service.UserService;
import com.example.package404.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration configuration;
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserService userService) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/user/signup2/{role}").permitAll()  // 로그인, 회원가입 허용
                        .requestMatchers("/board/**"  ,"/board/1").hasRole("STUDENT")
                        .requestMatchers("/instructor/**"  ,"/instructor/1").hasRole("INSTRUCTOR")
                        .requestMatchers("/instructor/**" , "/course/**").permitAll()
                        .anyRequest().authenticated()  // 나머지는 인증 필요
                )
                .addFilterAt(new LoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)  // 로그인 필터 추가
                .addFilterBefore(new JwtFilter(userService), UsernamePasswordAuthenticationFilter.class);  // JWT 필터 추가

        return http.build();
    }

}
