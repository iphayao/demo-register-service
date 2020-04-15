package com.iphayao.demo.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailService implements ReactiveUserDetailsService {
    private UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(user -> User.withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities(getAuthorities(user.getRole()))
                        .build());

    }

    private List<GrantedAuthority> getAuthorities(String roles) {
        return Collections.singletonList(new SimpleGrantedAuthority(roles));
    }

}
