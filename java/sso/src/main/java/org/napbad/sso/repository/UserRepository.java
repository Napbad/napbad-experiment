package org.napbad.sso.repository;

import org.napbad.sso.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findByUsername(String username) {
        return entityManager.find(User.class, username);
    }

    // Additional methods for saving, deleting, etc.
}
