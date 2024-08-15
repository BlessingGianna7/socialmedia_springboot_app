package com.example.socialmedia.Service;

import com.example.socialmedia.Models.Reels;
import com.example.socialmedia.Models.User;
import com.example.socialmedia.Repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService{

    @Autowired
private ReelsRepository reelsRepository;

    @Autowired
    private  UserService userService;


    @Override
    public Reels createReel(Reels reel, User user) {
        Reels createdReel = new Reels();
        createdReel.setTitle(reel.getTitle());
        createdReel.setUser(user);
        createdReel.setVideo(reel.getVideo());


        return reelsRepository.save(createdReel);
    }

    @Override
    public List<Reels> findAllReels() {

        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {


userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
