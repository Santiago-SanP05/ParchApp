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
import java.util.Date;

/**
 *
 * @author kevin
 */
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLike;
    
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User likeUser;
    
    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post likePost;
    
    @Temporal(TemporalType.DATE)
    private Date publication_date;

    public Like() {
    }

    public Like(Long idLike, User likeUser, Post likePost, Date publication_date) {
        this.idLike = idLike;
        this.likeUser = likeUser;
        this.likePost = likePost;
        this.publication_date = publication_date;
    }

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public User getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(User likeUser) {
        this.likeUser = likeUser;
    }

    public Post getLikePost() {
        return likePost;
    }

    public void setLikePost(Post likePost) {
        this.likePost = likePost;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }
    
    
}
