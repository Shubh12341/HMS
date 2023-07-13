package com.hospitalmanagement.repository;

import com.hospitalmanagement.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    Optional<Staff> findByEmail(String email);
    Optional<Staff> findByUsernameOrEmail(String username, String email);
    Optional<Staff> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
