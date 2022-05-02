package com.example.demo.services;

import com.example.demo.modules.DemoClass;
import com.example.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    private DemoRepository repository;

    @Autowired
    public DemoService(DemoRepository repository) {
        this.repository = repository;
    }

    public List<DemoClass> getDemo() {
        return repository.findAll();
    }
}
