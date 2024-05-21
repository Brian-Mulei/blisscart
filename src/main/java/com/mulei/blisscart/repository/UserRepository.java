package com.mulei.blisscart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulei.blisscart.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
