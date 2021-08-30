package com.crud.rest313.controller;

import com.crud.rest313.dto.RoleDto;
import com.crud.rest313.dto.UserDto;
import com.crud.rest313.dto.UserSimpleDto;
import com.crud.rest313.dto.mapper.UserMapper;
import com.crud.rest313.model.Role;
import com.crud.rest313.model.User;
import com.crud.rest313.service.RoleService;
import com.crud.rest313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdminRest {
    private final UserService userService;

    private final RoleService roleService;


    @Autowired
    public AdminRest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> userList = userService.findAllUsers();
//        userList.sort(Comparator.comparing(User::getUsername));
//        return userList.stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return (userList != null && !userList.isEmpty())
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        var user = userService.findUserById(id);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody UserSimpleDto userDto) {
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            roleSet.add(roleService.findByName(roleName));
        }
        var user = new User(userDto);
        user.setRoles(roleSet);
        userService.saveUser(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long id, @RequestBody UserSimpleDto userDto) {
        var user = new User(userDto);
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            roleSet.add(roleService.findByName(roleName));
        }
        user.setRoles(roleSet);
        user.setId(id);
        userService.updateUser(user);
        var updatedUser = userService.findUserById(user.getId());

        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> removeUser(@PathVariable Long userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
