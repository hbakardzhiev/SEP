package com.example.demo.repository;

import com.example.demo.modules.ActionValueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for ActionValueType class - enables Spring Data to find
 * all the basic interface & automatically create implementation
 * for CRUD features regarding ActionValueType class.
 */
@Repository
public interface ActionValueTypeRepository extends JpaRepository<ActionValueType, String> {

}
