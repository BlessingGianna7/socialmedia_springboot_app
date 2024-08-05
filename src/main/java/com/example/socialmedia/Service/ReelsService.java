package com.example.socialmedia.Service;

import com.example.socialmedia.Models.Reels;
import com.example.socialmedia.Models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);
    public List<Reels> findAllReels();
    public  List<Reels> findUsersReel(Integer userId);

}
