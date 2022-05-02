package com.example.demo.repository;

import com.example.demo.modules.DemoClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<DemoClass, Long> {}
