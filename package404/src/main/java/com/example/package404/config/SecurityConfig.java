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
                        .requestMatchers("/v3/**", "/v3/api-docs/**", "/swagger-ui/**",
                                "/swagger-ui.html", "/swagger-resources/**", "/favicon.ico").permitAll()
                        .requestMatchers("/board/**").hasAnyRole("STUDENT", "INSTRUCTOR", "MANAGER") // 게시판은 로그인한 회원이라면 모두 허용
                        .requestMatchers("/course/**").hasAnyRole("STUDENT", "INSTRUCTOR", "MANAGER") // 수업은 로그인한 회원이라면 모두 허용
                        .requestMatchers("/student/**").hasRole("STUDENT") // 학생 기능은 학생에게만 허용
                        .requestMatchers("/instructor/**").hasRole("INSTRUCTOR") // 강사 기능은 강사에게만 허용
                        .requestMatchers("/manager/**").hasRole("MANAGER") // 매니저 기능은 매니저에게만 허용
                        .anyRequest().authenticated()
                )
                .addFilterAt(new LoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)  // 로그인 필터 추가
                .addFilterBefore(new JwtFilter(userService), UsernamePasswordAuthenticationFilter.class);  // JWT 필터 추가

        return http.build();
    }

}
