package com.spring.orm.dao;

import com.spring.orm.entities.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public class StudentDao {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public int insert(Student student){
        this.entityManager.persist(student);
        return 1;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
