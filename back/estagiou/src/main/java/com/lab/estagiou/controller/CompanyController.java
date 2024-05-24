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
import com.lab.estagiou.dto.request.model.company.CompanyRegisterRequest;
import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(value = UtilController.API_VERSION + "/company", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Company", description = "API for management of companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Operation(summary = "Register company", description = "Register a company")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company registered successfully", content = @Content),
        @ApiResponse(responseCode = "400", description = "Email or CNPJ already registered or incorrects atributtes", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<Object> registerCompany(@RequestBody CompanyRegisterRequest request) {
        return companyService.registerCompany(request);
    }

    @Operation(summary = "List companies", description = "List all companies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Companies listed successfully"),
        @ApiResponse(responseCode = "204", description = "No companies found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyEntity>> listCompanies() {
        return companyService.listCompanies();
    }
    
    @Operation(summary = "Search company by ID", description = "Search a company by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company found successfully"),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyEntity> searchCompanyById(@PathVariable UUID id) {
        return companyService.searchCompanyById(id);
    }

    @Operation(summary = "Delete company by ID", description = "Delete a company by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Company deleted successfully", content = @Content),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCompanyById(@PathVariable UUID id, Authentication authentication) {
        return companyService.deleteCompanyById(id, authentication);
    }

    @Operation(summary = "Update company", description = "Update a company")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Company updated successfully", content = @Content),
        @ApiResponse(responseCode = "400", description = "Incorrects atributtes", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCompany(@PathVariable UUID id, @RequestBody CompanyRegisterRequest request, Authentication authentication) {
        return companyService.updateCompany(id, request, authentication);
    }

}
