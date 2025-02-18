package com.example.package404.instructor.repository;

import com.example.package404.instructor.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
