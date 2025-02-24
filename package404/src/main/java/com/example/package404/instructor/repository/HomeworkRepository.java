package com.example.package404.instructor.repository;


import com.example.package404.instructor.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeworkRepository  extends JpaRepository<Homework, Long> {

    Optional<Long> findIdxByIdx(Long idx);

}
