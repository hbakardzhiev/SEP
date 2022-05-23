package com.example.demo.repository;

import com.example.demo.modules.SheetSource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository that does CRUD operations on the SheetSource class
 * in the DB
 *
 */
public interface SheetSourceRepository extends JpaRepository<SheetSource, Long> {}