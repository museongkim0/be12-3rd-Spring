package com.example.package404.instructor.repository;


import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository  extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findById(Long userIdx);
}
