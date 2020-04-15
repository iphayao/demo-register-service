package com.iphayao.demo.user;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    @Query("SELECT * FROM users WHERE email = :email")
    Mono<User> findByEmail(String email);
}
