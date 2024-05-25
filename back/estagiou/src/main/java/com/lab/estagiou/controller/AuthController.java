package com.lab.estagiou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.estagiou.controller.util.UtilController;
import com.lab.estagiou.dto.request.auth.RequestAuthentication;
import com.lab.estagiou.dto.response.auth.LoginResponse;
import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.service.AuthorizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = UtilController.API_VERSION + "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Auth", description = "API for management of authentication")
public class AuthController {
   
    @Autowired
    private AuthorizationService authorizationService;

    @Operation(summary = "Create token", description = "Create token for user authentication")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company registered successfully", content = @Content),
        @ApiResponse(responseCode = "400", description = "User or password incorrects", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid RequestAuthentication authetinticationDto) {
        return authorizationService.login(authetinticationDto);
    }

    @Operation(summary = "Logout", description = "Logout user from system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Logout successfully", content = @Content),
        @ApiResponse(responseCode = "401", description = "Expired authentication", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        return authorizationService.logout(request);
    }

}
