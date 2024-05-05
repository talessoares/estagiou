package com.lab.estagiou.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.estagiou.dto.request.model.RequestRegisterStudent;
import com.lab.estagiou.model.student.StudentEntity;
import com.lab.estagiou.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(value = "/v1/student", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Student", description = "API for management of students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Register student", description = "Register a student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Student registered successfully", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<Object> registerCompany(@RequestBody RequestRegisterStudent request) {
        return studentService.registerCompany(request);
    }

    @Operation(summary = "List students", description = "List all students")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Students listed successfully"),
        @ApiResponse(responseCode = "204", description = "No students found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentEntity>> listStudents() {
        return studentService.listStudents();
    }
    
    @Operation(summary = "Search student by ID", description = "Search a student by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student found successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentEntity> searchStudentById(@PathVariable UUID id) {
        return studentService.searchStudentById(id);
    }

    @Operation(summary = "Delete student by ID", description = "Delete a student by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Student deleted successfully", content = @Content),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable UUID id) {
        return studentService.deleteStudentById(id);
    }

    @Operation(summary = "Update student", description = "Update a student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Student updated successfully", content = @Content),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable UUID id, @RequestBody RequestRegisterStudent requestCadastro) {
        return studentService.updateStudent(id, requestCadastro);
    }

}
