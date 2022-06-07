package com.example.demo.repository;

import com.example.demo.modules.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
  Admin findAdminByUsername(String username);
}
