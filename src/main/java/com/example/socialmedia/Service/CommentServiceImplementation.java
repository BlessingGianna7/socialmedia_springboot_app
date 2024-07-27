package com.example.socialmedia.Service;

import com.example.socialmedia.Models.Comment;
import com.example.socialmedia.Models.Post;
import com.example.socialmedia.Models.User;
import com.example.socialmedia.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.socialmedia.Service.UserService;
import  com.example.socialmedia.Service.PostService;
public class CommentServiceImplementation implements CommentService {


    @Autowired
private  PostService postService;

@Autowired
private UserService userService;


private CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) {

       User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        return null;


    }

    @Override
    public Comment findCommentById(Integer commentId) {
        return null;
    }

    @Override
    public Comment likeComment(Integer CommentId, Integer userId) {
        return null;
    }
}
