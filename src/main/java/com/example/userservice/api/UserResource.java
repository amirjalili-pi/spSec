package com.example.userservice.api;

import com.example.userservice.DTO.AddRoleToUserDTO;
import com.example.userservice.domain.AUser;
import com.example.userservice.domain.Role;
import com.example.userservice.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserResource {

    private final UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<List<AUser>> getUsers(){
        return new ResponseEntity<List<AUser>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/user/save")
    public ResponseEntity<AUser> saveUser(@RequestBody AUser user){
        return new ResponseEntity<AUser>(userService.saveUser(user), HttpStatus.CREATED);
    }


    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return new ResponseEntity<Role>(userService.saveRole(role), HttpStatus.CREATED);
    }

    @PostMapping("/role/add-to-user")
    public ResponseEntity<AUser> addRoleToUser(@RequestBody AddRoleToUserDTO form){
        userService.addUserRole(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}
