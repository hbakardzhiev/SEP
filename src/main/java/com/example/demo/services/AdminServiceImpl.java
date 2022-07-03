package com.example.demo.services;

import com.example.demo.Util;
import com.example.demo.modules.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService, UserDetailsService {

  private AdminRepository adminRepository;
  private final PasswordEncoder passwordEncoder;
  @Autowired
  public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
    this.adminRepository = adminRepository;
  }

  /**
   * Method that adds na Admin object to the database.
   *
   * @param admin the admin to be added
   * @return admin the object that was added
   */
  @Override
  public Admin addAdmin(Admin admin) {
    admin.setPassword(passwordEncoder.encode(admin.getPassword()));
    admin.setAdminRole("ADMIN");
    return adminRepository.save(admin);
  }

  @Override
  public Admin getAdminById(Long id) {
    return adminRepository.getById(id);
  }

  /**
   * Method that deletes an administrator from the database
   *
   * @param id the id of the admin object
   */
  @Override
  public void deleteAdmin(Long id) throws IllegalAccessException {

    final var username = Util.getUsernameFromPrincipal();

    if (getAdminById(id).getUsername().equals(username)) {
      throw new IllegalAccessException("User cannot delete themself");
    }

    adminRepository.deleteById(id);
  }

  /**
   * Method that returns a list of all admins in the database
   *
   * @return the list of admins found in the database
   */
  @Override
  public List<Admin> getAdmins() {
    return adminRepository.findAll();
  }

  /**
   * Find an admin in the database when given the username
   *
   * @param username the username to be searched
   * @return returns the details of the user
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final var admin = adminRepository.findAdminByUsername(username);

    if (admin == null) {
      throw new UsernameNotFoundException("Admin not found in database");
    }

    final var authorities = List.of(new SimpleGrantedAuthority("ADMIN"));

    return new org.springframework.security.core.userdetails.User(
        admin.getUsername(), admin.getPassword(), authorities);
  }
}
