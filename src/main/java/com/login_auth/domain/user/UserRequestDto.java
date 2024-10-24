package com.login_auth.domain.user;

public record UserRequestDto(String name,
                             String email,
                             String password) {
}