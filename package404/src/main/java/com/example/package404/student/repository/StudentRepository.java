package com.example.package404.student.repository;

import com.example.package404.student.model.StudentDetail;
import com.example.package404.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentDetail, Long> {

    @Query("SELECT sd FROM StudentDetail sd JOIN FETCH sd.user u")
    List<StudentDetail> findAllStudents();

    @Query("SELECT sd FROM StudentDetail sd JOIN FETCH sd.user u WHERE u.idx = :idx")
    Optional<StudentDetail> findByStudent(Long idx);
}