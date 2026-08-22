package com.Maarten0162.ProgressPicBackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Maarten0162.ProgressPicBackend.DAL.UserRepo;
import com.Maarten0162.ProgressPicBackend.model.User;
import com.Maarten0162.ProgressPicBackend.model.UserDTO;

@Service
public class AuthService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService( UserRepo userRepository, PasswordEncoder passwordEncoder, JWTService service) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = service;
    }

    public void register(UserDTO request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setEmail(request.email());

        user.setPassword(
            passwordEncoder.encode(request.password())
        );

        userRepository.save(user);
    }

    public String login(UserDTO request) {

        if (!userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email not registered");
        }

        
        User user = userRepository.findByEmail(request.email()).get();

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
        
            throw new RuntimeException("Wrong Password");
        }

        return jwtService.generateToken(user);

    }
}