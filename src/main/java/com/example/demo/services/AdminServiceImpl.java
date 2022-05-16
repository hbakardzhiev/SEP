package com.example.demo.services;

import com.example.demo.modules.Admin;
import com.example.demo.repository.AdminRepoistory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
   @Autowired
   AdminRepoistory adminRepoistory;

   @Override
   public Admin addAdmin(Admin admin) {
      adminRepoistory.save(admin);
      return admin;
   }

   @Override
   public List<Admin> getAdmins() {
      return adminRepoistory.findAll();
   }
}
