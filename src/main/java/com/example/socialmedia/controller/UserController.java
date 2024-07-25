package com.example.socialmedia.controller;


import com.example.socialmedia.Repository.UserRepository;
import com.example.socialmedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.socialmedia.Models.User;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> getUsers(){
List<User> users = userRepository.findAll();
return users;
    }
    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
User user = userService.findUserById(id);
return user;

    }


    @PutMapping("/api/users/{userId}")
    public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User user) throws Exception{
User reqUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user, reqUser.getId());
return  updatedUser;
    }

    @PutMapping("/api/users/follow/{userId2}")

 public User followUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws Exception {
       User reqUser = userService.findUserByJwt(jwt);
        User user= userService.followUser(reqUser.getId(),userId2);

        return  user;

 }

 @GetMapping("/api/users/search")
 public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;

 }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Integer userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user with ID: " + userId);
        }
    }

    @GetMapping("/api/users/profile")
    public  User getUserFromToken(@RequestHeader("Authorization")String jwt){

User user = userService.findUserByJwt(jwt);
user.setPassword(null);
        return user;
    }}
