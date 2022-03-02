package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.model.UserRole;

public interface UserRoleRepository extends GenericRepository<UserRole> {

    UserRole findByRoleName(String roleName);

}
