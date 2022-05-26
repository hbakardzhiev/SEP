package com.example.demo.controller;

import com.example.demo.modules.Admin;
import com.example.demo.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
  public ResponseEntity<List<Admin>> getAdmins() {
    return ResponseEntity.ok().body(adminService.getAdmins());
  }

  /**
   * HTTP request DELETE /admins/delete/{id}
   *
   * @param id the id of admin to be deleted
   * @return 200 OK status code
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteAdminById(@PathVariable(name = "id") long id) {
    adminService.deleteAdmin(id);
    return ResponseEntity.ok().body("deleted");
  }

  /**
   * HTTP request POST /admins/add
   *
   * @param admin the Admin object to be added
   * @return 201 CREATED if the admin was successfully added and the admin object in body response.
   */
  @PostMapping("/add")
  public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
    URI uri =
        URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/admins").toUriString());
    return ResponseEntity.created(uri).body(adminService.addAdmin(admin));
  }
}
