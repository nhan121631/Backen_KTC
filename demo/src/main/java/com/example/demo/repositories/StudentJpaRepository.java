package com.example.demo.repositories;

import com.example.demo.entities.Student;

import jakarta.persistence.QueryHint;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {

    @EntityGraph(attributePaths = { "department", "courses" })
    List<Student> findAll();

    StudentProjection findByEmail(String email);

    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    @EntityGraph(attributePaths = { "department", "courses" })
    Student findByName(String name);
}
