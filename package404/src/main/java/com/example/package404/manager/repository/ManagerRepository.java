package com.example.package404.manager.repository;

import com.example.package404.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<User, Long> {
}
