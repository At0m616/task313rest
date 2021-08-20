package com.crud.rest313.service;


import com.crud.rest313.model.User;

import java.util.List;


public interface UserService {
    void saveUser(User user, Long[] roles);

    void updateUser(User user, Long[] roles);

    User findUserById(long id);

    User findUserByEmail(String email);

    void removeUser(long id);

    List<User> findAllUsers();



}
