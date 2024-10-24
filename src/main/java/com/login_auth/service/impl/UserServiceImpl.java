package com.login_auth.service.impl;

import com.login_auth.domain.repository.UserRepository;
import com.login_auth.domain.user.User;
import com.login_auth.domain.user.UserDto;
import com.login_auth.domain.user.UserRequestDto;
import com.login_auth.infra.exception.ValidateException;
import com.login_auth.service.UserService;
import com.login_auth.service.validate.ValidateUserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidateUserRegister validateRegister;

    @Override
    @Transactional
    public void userRegister(UserRequestDto userRequest) {

        validateRegister.validate(userRequest);

        String passwordEncoded = passwordEncoder.encode(userRequest.password());

        User newUser = new User();
        newUser.setName(userRequest.name());
        newUser.setEmail(userRequest.email());
        newUser.setPassword(passwordEncoded);

        User savedUser = userRepository.save(newUser);

        new UserDto(savedUser);
    }
}