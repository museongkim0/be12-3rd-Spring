package com.example.package404.user.model;

import com.example.package404.student.model.StudentDetail;
import com.example.package404.user.repository.UserRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;


import org.apache.catalina.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



@Entity
@Getter
@Setter
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
    private LocalDate birth;
    private String role;
    private boolean enabled;

    @OneToOne(mappedBy = "user")
    private StudentDetail studentDetail;


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
//        GrantedAuthority authority = new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return role;
//            }
//        };

        authorities.add(authority);
        return authorities;
    }


public static UserDetails loadUserByEmail(String email, UserRepository userRepository) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));

    return user; // `User`가 `UserDetails`를 구현하므로 그대로 반환 가능
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
