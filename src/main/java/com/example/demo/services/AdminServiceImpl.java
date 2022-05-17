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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService, UserDetailsService {
   @Autowired
   AdminRepoistory adminRepoistory;

   private final PasswordEncoder passwordEncoder;

   @Override
   public Admin addAdmin(Admin admin) {
      admin.setPassword(passwordEncoder.encode(admin.getPassword()));
      admin.setAdminRole("ADMIN");
      return adminRepoistory.save(admin);
   }

   @Override
   public void deleteAdmin(Long id) {

      adminRepoistory.deleteById(id);

   }

   @Override
   public List<Admin> getAdmins() {
      return adminRepoistory.findAll();
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Admin admin = adminRepoistory.findAdminByUsername(username);

      if (admin == null) {
         throw new UsernameNotFoundException("Admin not found in database");
      }

      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("ADMIN"));

      return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), authorities);
   }
}
