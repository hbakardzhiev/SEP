package com.example.demo.services;

import com.example.demo.modules.Check;
import com.example.demo.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
  This class implements the business logic.
 */

@Service
public class CheckService {

    private CheckRepository checkRepository;

    @Autowired
    public CheckService(CheckRepository repository) {
        this.checkRepository = repository;
    }

    public List<Check> getCheck(){
        return checkRepository.findAll();
    }
}
