package com.springcourse.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springcourse.cruddemo.entity.Course;
import com.springcourse.cruddemo.entity.Instructor;
import com.springcourse.cruddemo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }

    @Transactional
    @Override
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Transactional
    @Override
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c where c.instructor.id=:instructorId",
                Course.class)
                .setParameter("instructorId", id);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "Join FETCH i.instructorDetail "
                        + "where i.id=:instructorId",
                Instructor.class)
                .setParameter("instructorId", id);

        Instructor instructor = query.getSingleResult();
        System.out.println(instructor);
        return instructor;
    }

}
