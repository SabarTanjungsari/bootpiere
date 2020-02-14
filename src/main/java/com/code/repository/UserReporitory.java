package com.code.repository;

import com.code.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReporitory extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByname(String name);
}
