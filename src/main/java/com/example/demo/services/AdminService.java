package com.example.demo.services;

import com.example.demo.modules.Admin;

import java.util.List;

public interface AdminService {
  Admin addAdmin(Admin admin);

  Admin getAdminById(Long id);

  List<Admin> getAdmins();

  void deleteAdmin(Long id) throws IllegalAccessException;
}
