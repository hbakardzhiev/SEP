package com.example.demo.repository;

import com.example.demo.modules.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Admin class - enables Spring Data to find all the basic interface & automatically
 * create implementation for CRUD features regarding Admin class.
 */
public interface AdminRepoistory extends JpaRepository<Admin, Long> {
  Admin findAdminByUsername(String username);
}
