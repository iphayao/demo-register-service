package com.iphayao.demo.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public Mono<ResponseEntity<UserDto>> registerNewUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        log.info("Creating new user for {}", user.getEmail());

        return userService.registerNewUserAccount(user)
                .map(u -> ResponseEntity.ok(userMapper.convertToDto(u)));

    }

    @GetMapping
    public Flux<UserDto> getAllUser() {
        return userService.retrieveAllUsers()
                .map(userMapper::convertToDto);
    }

}
