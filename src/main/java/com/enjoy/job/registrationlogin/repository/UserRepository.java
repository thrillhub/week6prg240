package com.enjoy.job.registrationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enjoy.job.registrationlogin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}