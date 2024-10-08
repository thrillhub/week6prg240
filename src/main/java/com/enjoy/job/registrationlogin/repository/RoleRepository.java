package com.enjoy.job.registrationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enjoy.job.registrationlogin.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
