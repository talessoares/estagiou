package com.lab.estagiou.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.lab.estagiou.dto.request.auth.RequestAuthentication;
import com.lab.estagiou.dto.request.model.student.StudentRegisterRequest;
import com.lab.estagiou.dto.response.auth.LoginResponse;
import com.lab.estagiou.service.AuthorizationService;
import com.lab.estagiou.service.StudentService;

@SpringBootTest
class AuthTests {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private StudentService studentService;

    private StudentRegisterRequest request;

    @BeforeEach
    void setup() {
        request = new StudentRegisterRequest();
        request.setEmail("a");
        request.setName("a");
        request.setPassword("a");
        request.setLastName("a");

        studentService.registerStudent(request);
    }

    @Test
    @DisplayName("Test generate token")
    void testGenerateToken() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail(request.getEmail());
        requestLogin.setPassword(request.getPassword());

        ResponseEntity<LoginResponse> response = authorizationService.login(requestLogin);

        if (response.getStatusCode().is2xxSuccessful()) {
            String token = response.getBody().toString();
            assertNotNull(token);
        } else {
            fail(response.getBody().toString());
        }
    }

    @Test
    @DisplayName("Test generate token with invalid email")
    void testGenerateTokenWithInvalidEmail() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail("b");
        requestLogin.setPassword(request.getPassword());

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Test generate token with invalid password")
    void testGenerateTokenWithInvalidPassword() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail(request.getEmail());
        requestLogin.setPassword("b");

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Test generate token with invalid email and password")
    void testGenerateTokenWithInvalidEmailAndPassword() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail("b");
        requestLogin.setPassword("b");

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Test generate token with empty email")
    void testGenerateTokenWithEmptyEmail() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail("");
        requestLogin.setPassword(request.getPassword());

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Test generate token with empty password")
    void testGenerateTokenWithEmptyPassword() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail(request.getEmail());
        requestLogin.setPassword("");

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Test generate token with empty email and password")
    void testGenerateTokenWithEmptyEmailAndPassword() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail("");
        requestLogin.setPassword("");

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Test generate token with null email")
    void testGenerateTokenWithNullEmail() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail(null);
        requestLogin.setPassword(request.getPassword());

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Test generate token with null password")
    void testGenerateTokenWithNullPassword() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail(request.getEmail());
        requestLogin.setPassword(null);

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Test generate token with null email and password")
    void testGenerateTokenWithNullEmailAndPassword() {
        RequestAuthentication requestLogin = new RequestAuthentication();
        requestLogin.setEmail(null);
        requestLogin.setPassword(null);

        try {
            authorizationService.login(requestLogin);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    
}
