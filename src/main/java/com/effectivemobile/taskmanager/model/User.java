package com.effectivemobile.taskmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", columnDefinition = "VARCHAR(40)", nullable = false)
    private String email;

    @Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
    private String name;

    @Column(name = "password", columnDefinition = "VARCHAR(100)", nullable = false)
    private String password;

    public User(String username, String email, String encode) {
        this.name = username;
        this.email = email;
        this.password = encode;
    }
}
