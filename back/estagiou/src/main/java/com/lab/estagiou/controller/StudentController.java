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
import com.lab.estagiou.dto.request.model.student.StudentRegisterRequest;
import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.student.StudentEntity;
import com.lab.estagiou.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(value = UtilController.API_VERSION + "/student", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Student", description = "API for management of students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Register student", description = "Register a student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student registered successfully"),
        @ApiResponse(responseCode = "400", description = "Email already registered", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<Object> registerStudent(@RequestBody StudentRegisterRequest request) {
        return studentService.registerStudent(request);
    }

    @Operation(summary = "List students", description = "List all students")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Students listed successfully"),
        @ApiResponse(responseCode = "204", description = "No students found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentEntity>> listStudents(Authentication authentication) {
        return studentService.listStudents();
    }
    
    @Operation(summary = "Search student by ID", description = "Search a student by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student found successfully", content = @Content(schema = @Schema(implementation = StudentEntity.class))),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> searchStudentById(@PathVariable UUID id, Authentication authentication) {
        return studentService.searchStudentById(id, authentication);
    }

    @Operation(summary = "Delete student by ID", description = "Delete a student by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Student deleted successfully", content = @Content),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable UUID id, Authentication authentication) {
        return studentService.deleteStudentById(id, authentication);
    }

    @Operation(summary = "Update student", description = "Update a student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Student updated successfully", content = @Content),
        @ApiResponse(responseCode = "400", description = "Incorrect atributtes", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Authentication expired", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable UUID id, @RequestBody StudentRegisterRequest requestCadastro, Authentication authentication) {
        return studentService.updateStudent(id, requestCadastro, authentication);
    }

}
