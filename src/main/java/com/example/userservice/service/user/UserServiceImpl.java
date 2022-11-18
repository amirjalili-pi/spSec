package com.example.userservice.service.user;

import com.example.userservice.domain.AUser;
import com.example.userservice.domain.Role;
import com.example.userservice.repo.RoleRepo;
import com.example.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserServiceInterface, UserDetailsService {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AUser user = getUser(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }
        log.info("User found in database: {}", user);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }


    @Override
    public AUser saveUser(AUser user) {
        log.info("saving user: {}", user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving role: {}", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addUserRole(String username, String roleName) {
        log.info("adding role {} to user {}", roleName, username);
        AUser AUser = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        AUser.getRoles().add(role);
    }

    @Override
    public AUser getUser(String username) {
        log.info("fetching user: {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AUser> getAllUsers() {
        log.info("fetching all users");
        return userRepo.findAll();
    }

}
