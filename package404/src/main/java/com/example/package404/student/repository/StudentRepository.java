package com.example.package404.student.repository;

import com.example.package404.student.model.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentDetail, Long> {
}
