package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface ParserRepository{
    public String parseElement();
}
