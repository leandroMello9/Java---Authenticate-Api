package com.auth.user.domain.request;

public record CreateUserRequest(
    String username,
    String password,
    String email
) {
}