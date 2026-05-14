package com.compulsory10.lab10server.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public abstract class AbstractRepository<T> {
    protected static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("QuizPU");
    private final Class<T> entityClass;

    protected AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public T findById(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        T result = entityManager.find(entityClass, id);
        entityManager.close();
        return result;
    }
    public List<T> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<T> results = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        entityManager.close();
        return results;}
    public T update(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        T merged = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return merged;}
    public static void closeFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
