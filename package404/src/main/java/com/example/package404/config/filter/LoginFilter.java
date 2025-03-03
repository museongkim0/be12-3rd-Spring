package com.example.package404.config.filter;

import com.example.package404.user.model.Dto.UserRequestDto;
import com.example.package404.user.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.package404.utils.JwtUtil;

import java.io.IOException;
import java.util.Collection;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // JSON 요청에서 사용자 정보 가져오기
            UserRequestDto.SignupRequest userDto = new ObjectMapper().readValue(request.getInputStream(), UserRequestDto.SignupRequest.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDto.getEmail(), userDto.getPassword()
            );

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();


        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        GrantedAuthority auth = authorities.iterator().next();
        String role = auth.getAuthority();
        String token = JwtUtil.generateToken(user.getIdx(), user.getEmail(), role);

        System.out.println(role+"@@@@@@@@@@@@@@@@@@@@"+token);
        System.out.println(role+"@@@@@@@@@@@@@@@"+token);

        Cookie cookie = new Cookie("Authorization", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);

        response.addCookie(cookie);
    }
}
