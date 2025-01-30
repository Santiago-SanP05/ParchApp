/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.post.domain;

import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.like.domain.Like;
import campus.u2.parchap.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author kevin
 */
@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User userPublication;
    
    private String caption;
    
    private String imageUrl;
    
    @Temporal(TemporalType.DATE)
    private Date publicationDate;
    
    @OneToMany(mappedBy = "commentPost")
    private List<Comment> comment1 = new ArrayList<>();
    
    @OneToMany(mappedBy = "likePost")
    private List<Like> like1 = new ArrayList<>();
    
    public Post() {
    }

    public Post(String caption, String imageUrl, Date publicationDate) {
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.publicationDate = publicationDate;
    }
    
    
    
    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public User getUserPublication() {
        return userPublication;
    }

    public void setUserPublication(User userPublication) {
        this.userPublication = userPublication;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
    
    
    public List<Comment> getComments() {
        return comment1;
    }

    public void addComment1(Comment comment) {
        this.comment1.add(comment);
        comment.setCommentPost(this);
        
    }
    
    public void removeComment1(Comment comment) {
        this.comment1.remove(comment);
        comment.setCommentPost(this);
    }
    
    public void addLike1(Like like) {
        this.like1.add(like);
        like.setLikePost(this);
        
    }
    
    public void removeLike1(Like like) {
        this.like1.remove(like);
        like.setLikePost(this);
    }

    public List<Like> getLike1() {
        return like1;
    }
    
}
