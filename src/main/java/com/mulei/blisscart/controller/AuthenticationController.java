package com.mulei.blisscart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mulei.blisscart.dto.UserDTO;
import com.mulei.blisscart.dto.VendorRegistrationDTO;
import com.mulei.blisscart.reponse.AuthenticationResponse;
import com.mulei.blisscart.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserDTO request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/register/vendor")
    public ResponseEntity<AuthenticationResponse> registeVendor(
            @RequestBody VendorRegistrationDTO request
            ) {
        return ResponseEntity.ok(authService.registerVendor(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserDTO request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return authService.refreshToken(request, response);
    }
}
