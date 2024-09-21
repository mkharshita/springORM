package com.spring.orm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.spring.orm.entities.Student;

import java.util.List;

@Repository
public class StudentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Student saveStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public Student getStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Transactional
    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        if (student != null) {
            entityManager.remove(student);
        }
    }

    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }
}
