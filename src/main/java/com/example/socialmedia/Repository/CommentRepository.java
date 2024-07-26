package com.example.socialmedia.Repository;

import com.example.socialmedia.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {


}
