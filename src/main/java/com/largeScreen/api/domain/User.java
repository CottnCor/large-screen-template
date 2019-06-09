package com.largeScreen.api.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbsys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_userid")
    private Long id;

    @Column(name = "f_username")
    private String username;

    @Column(name = "f_password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
