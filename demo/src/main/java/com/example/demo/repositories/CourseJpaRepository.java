package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Course;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {

    // Method to find all courses with their associated students
    @EntityGraph(attributePaths = { "students" })
    List<Course> findAll();

}
