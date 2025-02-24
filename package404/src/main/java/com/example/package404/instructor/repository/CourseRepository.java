package com.example.package404.instructor.repository;

import com.example.package404.instructor.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT DISTINCT c FROM Course c " +
            "LEFT JOIN FETCH c.sectionList " +
            "LEFT JOIN FETCH c.testList " +
            "LEFT JOIN FETCH c.instructor")
    List<Course> findAllWithAssociations();

}
