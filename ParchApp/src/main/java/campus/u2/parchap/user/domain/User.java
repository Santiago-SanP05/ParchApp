/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.user.domain;


import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.follower.domain.Follower;

import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.post.domain.Post;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    private String name;
    
    @Column(unique = true)
    private String nameUser;
    
    private String urlPhoto;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    private String biography;
    
    private LocalDateTime createDate;
    
    private LocalDateTime updateDate;
    
    @OneToMany(mappedBy = "userFollower", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follower> followers  = new ArrayList<>();
    
    @OneToMany(mappedBy = "userFollowed", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follower> followeds = new ArrayList<>();
    
    @OneToMany(mappedBy = "userPublication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "userComment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    
    @OneToMany(mappedBy = "userLike", cascade = CascadeType.ALL)
    private List<Reaction> like1 = new ArrayList<>();
    
    public User() {
    }

    public User(String name, String nameUser, String urlPhoto, String email, String password, String biography, LocalDateTime createDate, LocalDateTime updateDate) {
        
        this.name = name;
        this.nameUser = nameUser;
        this.urlPhoto = urlPhoto;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.createDate = createDate;
        this.updateDate = updateDate;
        
        
    }

    public Long getId_User() {
        return idUser;
    }

    public void setId_User(Long Id_User) {
        this.idUser = Id_User;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public List<Follower> getFollower() {
        return followers;
    }

    public void addFollower(Follower follower) {
        this.followers.add(follower);
        follower.setUserFollower(this);
    }
    
    public void removeFollower(Follower follower) {
        this.followers.remove(follower);
        follower.setUserFollower(null);
    }

    public List<Follower> getFollowed() {
        return followeds;
    }

    public void addFollowed(Follower followed) {
        this.followeds.add(followed);
        followed.setUserFollowed(this);
        
    }
    
    public void removeFollowed(Follower followed) {
        this.followeds.remove(followed);
        followed.setUserFollowed(null);
    }

    public List<Post> getPost() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.setUserPublication(this);
    }

    public void removePost(Post post) {
        this.posts.remove(post);
        post.setUserPublication(this);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment) {
        this.comments.add(comment);
        comment.setCommentUser(this);
    }
    
    public void removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setCommentUser(this);
    }
    
    public void addLike1(Reaction like){
        this.like1.add(like);
        like.setLikeUser(this);
    }
    
    public void removeLike1(Reaction like){
        this.like1.remove(like);
        like.setLikeUser(this);
    }

    public List<Reaction> getLike1() {
        return like1;
    }

}
