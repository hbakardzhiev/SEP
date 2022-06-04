package com.example.demo.repository;

import com.example.demo.modules.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Action class - enables Spring Data to find all the basic interface &
 * automatically create implementation for CRUD features regarding Action class.
 */
@Repository
public interface ActionRepository extends JpaRepository<Action, String> {}
