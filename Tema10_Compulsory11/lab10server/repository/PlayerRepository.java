package com.compulsory10.lab10server.repository;

import com.compulsory10.lab10server.model.PlayerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class PlayerRepository extends AbstractRepository<PlayerEntity> {
    public PlayerRepository() {
        super(PlayerEntity.class);
    }

    public PlayerEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT p FROM PlayerEntity p WHERE p.name = :name", PlayerEntity.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } finally {
            entityManager.close();
        }
    }
}
