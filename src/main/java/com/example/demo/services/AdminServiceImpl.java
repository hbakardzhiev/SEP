package com.example.demo.services;

import com.example.demo.modules.Admin;
import com.example.demo.repository.AdminRepoistory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService, UserDetailsService {
  @Autowired AdminRepoistory adminRepoistory;

  private final PasswordEncoder passwordEncoder;

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
    return adminRepoistory.save(admin);
  }

  /**
   * Method that deletes an administrator from the database
   *
   * @param id the id of the admin object
   */
  @Override
  public void deleteAdmin(Long id) {

    adminRepoistory.deleteById(id);
  }

  /**
   * Method that returns a list of all admins in the database
   *
   * @return the list of admins found in the database
   */
  @Override
  public List<Admin> getAdmins() {
    return adminRepoistory.findAll();
  }

  /**
   * Find an admin in the databse when given the usernname
   *
   * @param username the username to be searched
   * @return returns the details of the user
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Admin admin = adminRepoistory.findAdminByUsername(username);

    if (admin == null) {
      throw new UsernameNotFoundException("Admin not found in database");
    }

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ADMIN"));

    return new org.springframework.security.core.userdetails.User(
        admin.getUsername(), admin.getPassword(), authorities);
  }
}
