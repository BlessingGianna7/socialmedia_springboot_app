package com.example.socialmedia.Service;

import com.example.socialmedia.Models.Post;
import com.example.socialmedia.Models.User;
import com.example.socialmedia.Repository.PostRepository;
import com.example.socialmedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImplementation implements PostService{



    @Autowired
   PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        newPost.setCreatedAt( LocalDateTime.now());

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user= userService.findUserById(userId);
if(!Objects.equals(post.getUser().getId(), user.getId())){
    throw new Exception("you can't delete another user's posts");
}
        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostsByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> otp = postRepository.findById(postId);
        if(otp.isEmpty()){
            throw  new Exception("Post not found with id"+ postId);
        }

        return otp.get();
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPosts().contains(post)){
            user.getSavedPosts().remove(post);
        }
        else{
            user.getSavedPosts().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
Post post = findPostById(postId);
User user = userService.findUserById(userId);
if(post.getLiked().contains(user)){
    post.getLiked().remove(user);
}else{
    post.getLiked().add(user);
}

        return postRepository.save(post);
    }
}
