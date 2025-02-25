package com.example.package404.manager.repository;

import com.example.package404.manager.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    @Query("SELECT t FROM Test t JOIN FETCH t.course")
    List<Test> findAllWithCourse();
}
