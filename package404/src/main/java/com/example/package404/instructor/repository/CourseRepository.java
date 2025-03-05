package com.example.package404.instructor.repository;

import com.example.package404.instructor.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT DISTINCT c FROM Course c " +
            "LEFT JOIN FETCH c.curriculumList " +
            "LEFT JOIN FETCH c.testList " +
            "LEFT JOIN FETCH c.instructor")
    List<Course> findAllWithAssociations();

    @Query("SELECT c FROM Course c")
    List<Course> findAllCourses();


    @Query("SELECT c FROM Course c JOIN FETCH c.curriculumList")
    List<Course> findAllWithcurriculumList();


    // n+1 문제 해결 안됐음
    @Query("SELECT c FROM Course c JOIN FETCH c.instructor i WHERE i.userIdx = :userIdx")
    List<Course> findByInstructorUserIdx(@Param("userIdx") Long userIdx);

    @Query("SELECT c FROM Course c JOIN FETCH c.curriculumList WHERE c.generation = :generation")
    Course findAllWithCurriculumListByGeneration(@Param("generation") int generation);


}
