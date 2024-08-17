package com.example.socialmedia.controller;


import com.example.socialmedia.Models.Reels;
import com.example.socialmedia.Models.User;
import com.example.socialmedia.Service.ReelsService;
import com.example.socialmedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
   @Autowired
   private ReelsService reelsService;
   @Autowired
   private UserService userService;

   @PostMapping("/api/reels")
   public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){

    User reqUser =userService.findUserByJwt(jwt);
      Reels createdReels = reelsService.createReel(reel,reqUser);
   return  createdReels;
   }


   @GetMapping("/api/reels")
   public Reels findAllReels(){

      List<Reels> reels = reelsService.findAllReels();

      return (Reels) reels;
   }


   @GetMapping("/api/reels/user/{userId}")
   public Reels findUsersReels(@PathVariable Integer userId) throws Exception {

      List<Reels> reels = reelsService.findUsersReel(userId);

      return (Reels) reels;
   }
}
