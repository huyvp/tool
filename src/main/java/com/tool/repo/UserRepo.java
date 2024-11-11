package com.tool.repo;

import com.tool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByIp(String ip);

    Optional<User> findByUsername(String username);
}