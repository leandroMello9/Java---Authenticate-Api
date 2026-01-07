package com.auth.user.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(generator = "uuidv7-generator")
    @GenericGenerator(name = "uuidv7-generator", type = com.auth.user.config.UUIDV7ConfigGenerator.class)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime creadtedAt;

    @UpdateTimestamp
    @Column(updatable = false, name = "updated_at")
    private LocalDateTime  updatedAt;


    public UserEntity(
        String userName,
        String userEmail,
        String userPassword
    ) {

        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

}