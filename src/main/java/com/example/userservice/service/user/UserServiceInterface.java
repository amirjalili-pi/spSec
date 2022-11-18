package com.example.userservice.service.user;

import com.example.userservice.domain.AUser;
import com.example.userservice.domain.Role;

import java.util.List;

public interface UserServiceInterface {
    AUser saveUser(AUser user);

    Role saveRole(Role role);

    void addUserRole(String username, String roleName);

    AUser getUser(String username);

    List<AUser> getAllUsers();
}
