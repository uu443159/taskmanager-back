package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.model.UserRole;
import com.stefanini.taskmanager.repository.UserRoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepositoryImpl extends GenericRepositoryImpl<UserRole> implements UserRoleRepository {

    @Override
    protected Class<UserRole> getEntityClass() {
        return UserRole.class;
    }

    @Override
    protected String getQuery() {
        return null;
    }
}
