package com.auth.user.domain.response;

public record CreateUserResponse(
    String username,
    String email
) {
}
