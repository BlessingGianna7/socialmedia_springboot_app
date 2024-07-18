package com.example.socialmedia.controller;


import com.example.socialmedia.Models.User;
import com.example.socialmedia.Repository.UserRepository;
import com.example.socialmedia.Response.AuthResponse;
import com.example.socialmedia.Service.UserService;
import com.example.socialmedia.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{

        User isExist = userRepository.findByEmail(user.getEmail());
        if(isExist!= null){
            throw new Exception("this email already used with another account");
        }

User newUser= new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));


        User savedUser =  userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
String token= JwtProvider.generateToken(authentication);

    AuthResponse res = new AuthResponse(token, "Register success");

        return res;

}
}
