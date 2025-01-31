/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.like.domain;

import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author kevin
 */
@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLike;
    
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User userLike;
    
    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post likePost;
    
    
    private LocalDateTime publication_date;

    public Reaction() {
    }

    public Reaction(LocalDateTime publication_date) {
        this.publication_date = publication_date;
    }

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public User getLikeUser() {
        return userLike;
    }

    public void setLikeUser(User userLike) {
        this.userLike = userLike;
    }

    public Post getLikePost() {
        return likePost;
    }

    public void setLikePost(Post likePost) {
        this.likePost = likePost;
    }

    public LocalDateTime getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(LocalDateTime publication_date) {
        this.publication_date = publication_date;
    }
    
    
}
