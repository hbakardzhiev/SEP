package com.example.demo.services;

import com.example.demo.repository.AdminRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepoistory adminRepoistory;


}
