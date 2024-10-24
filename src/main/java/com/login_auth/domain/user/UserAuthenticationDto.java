package com.login_auth.domain.user;

public record UserAuthenticationDto(String email,
                                    String password) {
}