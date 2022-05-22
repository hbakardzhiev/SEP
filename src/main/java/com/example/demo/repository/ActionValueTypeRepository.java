package com.example.demo.repository;

import com.example.demo.modules.ActionValueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionValueTypeRepository extends JpaRepository<ActionValueType, String> {

}
