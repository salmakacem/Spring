package com.DPC.spring.controllers;

import com.DPC.spring.entities.User;
import com.DPC.spring.exceptions.EmailAlreadyUsedException;
import com.DPC.spring.payload.requests.LoginRequest;
import com.DPC.spring.payload.requests.RegisterRequest;
import com.DPC.spring.payload.responses.LoginResponse;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.repositories.UserRepository;
import com.DPC.spring.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        LoginResponse token = this.authService.login(loginRequest);
        return ResponseEntity.ok(token);
         }


    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws EmailAlreadyUsedException {
        String message = this.authService.register(registerRequest);
        return ResponseEntity.ok(new MessageResponse(message));
    }
}
