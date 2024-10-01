package com.example.socialmedia.controller;

import com.example.socialmedia.Models.User;
import com.example.socialmedia.Repository.UserRepository;
import com.example.socialmedia.Response.AuthResponse;
import com.example.socialmedia.Service.CustomerUserDetailsService;
import com.example.socialmedia.Service.UserService;
import com.example.socialmedia.config.JwtProvider;
import com.example.socialmedia.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    // 1. Sign-up endpoint
    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody User user) {
        // Check if email is already registered
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already registered.");
        }

        // Create and save new user
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        Authentication authentication = authenticate(newUser.getEmail(), user.getPassword());

        // Generate JWT token
        String token = JwtProvider.generateToken(authentication);

        // Return response with token
        return new AuthResponse(token, "Registration and login successful");
    }

    // 2. Login endpoint
    @PostMapping("/login")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        // Generate JWT token
        String token = JwtProvider.generateToken(authentication);

        // Return response with token
        return new AuthResponse(token, "Login success");
    }

    // 3. Private helper method to authenticate user
    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        // Handle invalid email
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid email");
        }

        // Handle invalid password
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Password does not match");
        }

        // Create authentication token
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
