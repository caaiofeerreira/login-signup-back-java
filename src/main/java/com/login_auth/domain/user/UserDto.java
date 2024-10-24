package com.login_auth.domain.user;

public record UserDto(String name,
                      String email,
                      String password) {

    public UserDto(User user) {
        this(user.getName(),
                user.getEmail(),
                user.getPassword());
    }
}