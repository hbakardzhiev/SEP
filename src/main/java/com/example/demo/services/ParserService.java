package com.example.demo.services;

import com.example.demo.repository.ParserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService {

    @Autowired
    private ParserRepository repository;

    public String parse() {
        try {
            return repository.parseElement();
        }
        catch (Exception ex) {
            System.out.println("problem");
        }
        return "";
    }

}
