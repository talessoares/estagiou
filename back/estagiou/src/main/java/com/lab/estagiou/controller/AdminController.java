package com.lab.estagiou.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.estagiou.controller.util.UtilController;
import com.lab.estagiou.dto.request.model.admin.AdminRegisterRequest;
import com.lab.estagiou.model.admin.AdminEntity;
import com.lab.estagiou.service.AdminService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(value = UtilController.API_VERSION + "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Admin", description = "API for management of admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody AdminRegisterRequest request) {
        return adminService.registerAdmin(request);
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminEntity>> listAdmins(Authentication authentication) {
        return adminService.listAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> searchAdminById(@PathVariable UUID id, Authentication authentication) {
        return adminService.searchAdminById(id, authentication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdminById(@PathVariable UUID id, Authentication authentication) {
        return adminService.deleteAdminById(id, authentication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdmin(@PathVariable UUID id, @RequestBody AdminRegisterRequest requestCadastro, Authentication authentication) {
        return adminService.updateAdmin(id, requestCadastro, authentication);
    }
    
}
