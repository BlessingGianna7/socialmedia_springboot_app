package com.example.socialmedia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.socialmedia.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
public User findByEmail(String email);

@Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
public List<User> searchUser(@Param("query") String query);
}
