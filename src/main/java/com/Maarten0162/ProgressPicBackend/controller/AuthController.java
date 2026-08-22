package com.Maarten0162.ProgressPicBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Maarten0162.ProgressPicBackend.model.UserDTO;
import com.Maarten0162.ProgressPicBackend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService service;

    private AuthController(AuthService serv) {
        this.service = serv;
    }

    @PostMapping("/login")
    public String Login(@RequestBody UserDTO dto) {
        return service.login(dto);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDTO dto) {
        service.register(dto);
    }

}
