package com.login_auth.service.validate;

import com.login_auth.domain.repository.UserRepository;
import com.login_auth.domain.user.User;
import com.login_auth.domain.user.UserDto;
import com.login_auth.domain.user.UserRequestDto;
import com.login_auth.infra.exception.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidateUserRegister {

    @Autowired
    private UserRepository userRepository;

    public void validate(UserRequestDto userRequest) {

        if (!userRequest.name().matches("^[A-Za-zÀ-ÿ\\s'-]+$")) {
            throw new ValidateException("Nome inválido. O nome deve conter apenas letras, espaços e caracteres especiais permitidos.");
        }

        Optional<User> newUser = userRepository.findOptionalByEmail(userRequest.email());

        newUser.ifPresent(user -> {
            throw new ValidateException("Este email já está cadastrado. Por favor, utilize um email diferente ou faça login.");
        });

        if (!userRequest.password().matches(".{6,}")) {
            throw new ValidateException("A senha deve conter pelo menos 6 caracteres.");
        }
    }
}