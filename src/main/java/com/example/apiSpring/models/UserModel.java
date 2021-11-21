package com.example.apiSpring.models;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name ="User")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, unique = true, nullable = false)
    private String email;
    @Column(length = 20, unique = false, nullable = false)
    private String username;
    @Column(length = 20, unique = false, nullable = false)
    private String password;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}