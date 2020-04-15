package com.iphayao.demo.user;

import com.iphayao.demo.exception.UserRegisteredException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<User> registerNewUserAccount(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.findByEmail(user.getEmail())
                .flatMap(e -> Mono.error(new UserRegisteredException(e.getEmail())))
                .switchIfEmpty(userRepository.save(user))
                .cast(User.class);
    }

    public Flux<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

}
