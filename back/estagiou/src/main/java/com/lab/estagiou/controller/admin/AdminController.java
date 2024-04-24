package com.lab.estagiou.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastroAdmin;
import com.lab.estagiou.service.admin.AdminService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(value = "/v1/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Admin", description = "API para gerenciamento de admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody RequestCadastroAdmin request, Authentication authentication) {
        return adminService.register(request, authentication);
    }
    
}
