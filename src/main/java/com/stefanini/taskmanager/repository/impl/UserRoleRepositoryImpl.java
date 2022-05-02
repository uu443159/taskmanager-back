package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.model.UserRole;
import com.stefanini.taskmanager.repository.UserRoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserRoleRepositoryImpl extends GenericRepositoryImpl<UserRole> implements UserRoleRepository {

    @Override
    protected Class<UserRole> getEntityClass() {
        return UserRole.class;
    }

    @Override
    public UserRole findByRoleName(String roleName) {
        Query query = entityManager.createQuery("SELECT r FROM " + getEntityClass().getSimpleName() + " r WHERE r.roleName=:roleName");
        query.setParameter("roleName", roleName);
        UserRole userRole = (UserRole) query.getSingleResult();

        return userRole;
    }
}
