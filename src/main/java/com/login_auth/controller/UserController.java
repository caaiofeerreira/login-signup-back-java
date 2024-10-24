package com.login_auth.controller;

import com.login_auth.domain.user.User;
import com.login_auth.domain.user.UserAuthenticationDto;
import com.login_auth.domain.user.UserDto;
import com.login_auth.domain.user.UserRequestDto;
import com.login_auth.infra.exception.UnauthorizedAccessException;
import com.login_auth.infra.security.DataTokenJWT;
import com.login_auth.infra.security.TokenService;
import com.login_auth.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DataTokenJWT> login(@RequestBody @Valid UserAuthenticationDto data) {

        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

            return ResponseEntity.status(HttpServletResponse.SC_OK).body(new DataTokenJWT(tokenJWT));
        } catch (AuthenticationException e) {
            throw new UnauthorizedAccessException("Credenciais inválidas fornecidas. Verifique seu email e password e tente novamente.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<DataTokenJWT> register(@RequestBody @Valid UserRequestDto userRequest) {

        userService.userRegister(userRequest);
        var authenticationToken = new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.status(HttpStatus.CREATED).body(new DataTokenJWT(tokenJWT));
    }
}