package com.crud.rest313.service;

import com.crud.rest313.dao.UserDao;
import com.crud.rest313.model.Role;
import com.crud.rest313.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public void saveUser(User user) {

        if ((roleService.findById(1L) == null)
                || (roleService.findById(2L) == null)) {
            roleService.save(new Role(1L, "ROLE_ADMIN"));
            roleService.save(new Role(2L, "ROLE_USER"));
        }

        if (userDao.findUserByUsername(user.getUsername()) == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.save(user);
        } else {
            try {
                throw new Exception("User exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (user.getId() == null) {
            try {
                throw new Exception("User not exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        var userFindDB = findUserById(user.getId());

        userFindDB.setFirstname(user.getFirstname());
        userFindDB.setLastname(user.getLastname());
        userFindDB.setAge(user.getAge());
        userFindDB.setUsername(user.getUsername());
        if (user.getPassword().equals("") || user.getPassword() == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }else {
            userFindDB.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDao.save(userFindDB);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(long id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByEmail(String name) {
        var userFindDB = userDao.findUserByUsername(name);
        if (userFindDB == null) {
            throw new UsernameNotFoundException("User not exist");
        }
        return userFindDB;
    }


    @Transactional
    @Override
    public void removeUser(long id) {
        userDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

}
