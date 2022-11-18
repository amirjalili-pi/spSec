package com.example.userservice.repo;

import com.example.userservice.domain.AUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AUser, Long> {
    AUser findByUsername(String username);
}
