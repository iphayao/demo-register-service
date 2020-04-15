package com.iphayao.demo.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table("users")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;
}
