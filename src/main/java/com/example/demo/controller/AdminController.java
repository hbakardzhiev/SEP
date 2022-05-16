package com.example.demo.controller;

import com.example.demo.modules.Admin;
import com.example.demo.repository.AdminRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class AdminController {


        @Autowired
        AdminRepoistory adminRepoistory;

        @GetMapping("/admins")
        public ResponseEntity<List<Admin>> getAllAdmins () {
            try {
                List<Admin> admins = adminRepoistory.findAll();

                if (admins.isEmpty()) {
                    return new ResponseEntity<>(admins, HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(admins, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        @DeleteMapping("/admins/{id}")
        public ResponseEntity<HttpStatus> deleteAdminById (@PathVariable long id) {
            try {
                adminRepoistory.deleteById(id);


                return new ResponseEntity<> (HttpStatus.OK);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        @PostMapping("/admins")
        public ResponseEntity<Admin> addAdmin (Admin admin) {
            try {
                if (adminRepoistory.findAdminByUsername(admin.getUsername()) == null) {
                    Admin _admin = adminRepoistory
                            .saveAndFlush(new Admin(admin.getUsername(), admin.getPassword()));

                    return new ResponseEntity<>(_admin, HttpStatus.OK);
                }

                return new ResponseEntity<>(null, HttpStatus.CONFLICT);

            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
