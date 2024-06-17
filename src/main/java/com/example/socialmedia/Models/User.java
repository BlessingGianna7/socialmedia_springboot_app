package com.example.socialmedia.Models;



import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    @ElementCollection
    private List<Integer> followings = new ArrayList<>();

    @ElementCollection
    private List<Integer> followers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_saved_posts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> savedPosts = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String email, String password, String gender, List<Integer>followers, List<Integer> followings, List<Post>
savedPosts    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender= gender;
        this.followers= followers;
        this.followings= followings;
        this.savedPosts= savedPosts;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public List<Integer> getFollowings() {
        return followings;
    }


    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }


    public List<Integer> getFollowers() {
        return followers;
    }


    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<Post> savedPosts) {
        this.savedPosts = savedPosts;
    }
}
