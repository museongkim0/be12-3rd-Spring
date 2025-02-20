package com.example.package404.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String email;
    private String password;
    private String name;
    private String role;

    @ElementCollection(fetch = FetchType.EAGER) // 다중 권한 저장
    private List<String> roles = new ArrayList<>();

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return email;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        GrantedAuthority authority = new SimpleGrantedAuthority(role);
////        GrantedAuthority authority = new GrantedAuthority() {
////            @Override
////            public String getAuthority() {
////                return role;
////            }
////        };
//
//        authorities.add(authority);
//        return authorities;
//    }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
}


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
