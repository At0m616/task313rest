package com.crud.rest313.service;


import com.crud.rest313.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    void save(Role role);

    Role findById(Long id);

    Role findByName(String name);

    Set<Role> findRolesSetById(Long[] id);

    Set<Role> findRolesSetByName(String[] names);

    List<Role> findAllRoles();
}
