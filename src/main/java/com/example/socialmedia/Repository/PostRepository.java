
package com.example.socialmedia.Repository;

import com.example.socialmedia.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.user.id=:userId")
    List<Post> findPostsByUserId(Integer userId);
}