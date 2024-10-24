package com.login_auth.service;

import com.login_auth.domain.user.UserRequestDto;

public interface UserService {

    void userRegister(UserRequestDto requestDto);
}