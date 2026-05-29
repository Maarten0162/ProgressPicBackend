package com.Maarten0162.ProgressPicBackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Maarten0162.ProgressPicBackend.model.User;
import com.Maarten0162.ProgressPicBackend.service.UserService;

@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService service;
    
    public UserController(UserService userService) {
        this.service = userService;
    }

    @PostMapping()
    public User createUser(@RequestBody User newuser) throws Exception {
        return service.createUser(newuser);
    }

}
