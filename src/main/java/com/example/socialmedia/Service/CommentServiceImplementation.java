package com.example.socialmedia.Service;

import com.example.socialmedia.Models.Comment;
import com.example.socialmedia.Models.Post;
import com.example.socialmedia.Models.User;
import com.example.socialmedia.Repository.CommentRepository;
import com.example.socialmedia.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.socialmedia.Service.UserService;
import  com.example.socialmedia.Service.PostService;
import java.util.Optional;

import java.time.LocalDateTime;

public class CommentServiceImplementation implements CommentService {


    @Autowired
private  PostService postService;

@Autowired
private UserService userService;

@Autowired
private PostRepository postRepository;


@Autowired
private CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) {

       User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
         Comment savedComment= commentRepository.save(comment);

         post.getComments().add(savedComment);
         postRepository.save(post);

        return savedComment;


    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception{
Optional<Comment> opt = commentRepository.findById(commentId);

if(opt.isEmpty()){
  throw new Exception("comment does not exist") ;
}

        return opt.get();
    }

    @Override
    public Comment likeComment(Integer CommentId, Integer userId) throws Exception {
      Comment comment= findCommentById(CommentId);
User user = userService.findUserById(userId);

if(!comment.getLikes().contains(user)){
    comment.getLikes().add(user);

}
else comment.getLikes().remove(user);

        return commentRepository.save(comment);
    }
}
