package com.seshadri.airesumeanalyzer.repository;

import com.seshadri.airesumeanalyzer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}