package com.example.socialmedia.Service;
import java.util.List;
import java.util.Optional;

import com.example.socialmedia.Repository.UserRepository;
import com.example.socialmedia.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.socialmedia.Models.User;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        User savedUser =  userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("user not exist with userid"+ userId);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws Exception{
        User  reqUser = findUserById(reqUserId);
        User user2= findUserById(userId2);
        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {

        Optional<User> user1= userRepository.findById(userId);

        if (user1.isEmpty()){
            throw new Exception("User with id doesn't exist"+ userId);
        }

        User oldUser =user1.get();

        if(user.getFirstName()!= null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }

        User updatedUser= userRepository.save(oldUser);
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {

        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public void deleteUserById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
    }


}
