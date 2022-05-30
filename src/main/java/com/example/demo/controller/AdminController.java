package com.example.demo.controller;

import com.example.demo.modules.Admin;
import com.example.demo.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
@CrossOrigin(CorsConfiguration.ALL)
public class AdminController {
  private final AdminService adminService;

  /**
   * GET /admins
   *
   * @return the list of admins in the database
   */
  @GetMapping()
  public List<Admin> getAdmins() {
    return adminService.getAdmins();
  }

  /**
   * HTTP request DELETE /admins/delete/{id}
   *
   * @param id the id of admin to be deleted
   * @return 200 OK status code
   */
  @DeleteMapping("/delete/{id}")
  public void deleteAdminById(@PathVariable(name = "id") long id) {
    adminService.deleteAdmin(id);
  }

  /**
   * HTTP request POST /admins/add
   *
   * @param admin the Admin object to be added
   * @return 201 CREATED if the admin was successfully added and the admin object in body response.
   */
  @PostMapping("/add")
  public Admin addAdmin(@RequestBody Admin admin) {
    return adminService.addAdmin(admin);
  }
}
