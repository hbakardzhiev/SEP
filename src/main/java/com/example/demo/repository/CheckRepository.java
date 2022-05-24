package com.example.demo.repository;

import com.example.demo.modules.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Check class - enables Spring Data to find all the basic interface & automatically
 * create implementation for CRUD features regarding Check class.
 */
@Repository
public interface CheckRepository extends JpaRepository<Check, String> {}
