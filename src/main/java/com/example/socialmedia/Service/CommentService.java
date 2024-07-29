package com.example.socialmedia.Service;

import com.example.socialmedia.Models.Comment;
import org.hibernate.annotations.Comments;

public interface CommentService {

    public Comment createComment(Comment comment, Integer postId, Integer userId);
    public Comment findCommentById(Integer commentId) throws Exception;

    public Comment likeComment(Integer CommentId, Integer userId) throws  Exception;

}
