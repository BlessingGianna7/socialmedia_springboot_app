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

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        User savedUser= userService.registerUser(user);
        return  savedUser;
    }
    @GetMapping("/users")
    public List<User> getUsers(){
List<User> users = userRepository.findAll();
return users;
    }
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
User user = userService.findUserById(id);
return user;

    }


    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception{
User updatedUser = userService.updateUser(user, userId);
return  updatedUser;
    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
 public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user= userService.followUser(userId1,userId2);
        return  user;

 }

 @GetMapping("/users/search")
 public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;

 }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Integer userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user with ID: " + userId);
        }
    }

}
