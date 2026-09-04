package com.seshadri.airesumeanalyzer.controller;

import com.seshadri.airesumeanalyzer.dto.RegisterRequest;
import com.seshadri.airesumeanalyzer.entity.User;
import com.seshadri.airesumeanalyzer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.seshadri.airesumeanalyzer.dto.LoginRequest;
import com.seshadri.airesumeanalyzer.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }
    
    @PostMapping("/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

    String token = userService.loginUser(
            request.getEmail(),
            request.getPassword()
    );

    LoginResponse response = new LoginResponse(
            "Login successful",
            token
    );

    return ResponseEntity.ok(response);
}
}