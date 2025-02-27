package com.login_auth.domain.repository;

import com.login_auth.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findOptionalByEmail(String email);

}