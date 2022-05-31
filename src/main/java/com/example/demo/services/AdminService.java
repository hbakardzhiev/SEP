package com.example.demo.services;

import com.example.demo.modules.Admin;

import java.util.List;

public interface AdminService {
  Admin addAdmin(Admin admin);

  List<Admin> getAdmins();

  void deleteAdmin(Long id);
}
