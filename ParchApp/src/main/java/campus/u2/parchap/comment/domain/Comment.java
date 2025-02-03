
package campus.u2.parchap.comment.domain;

import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;
    
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User userComment;
    
    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post commentPost;
    
    private String text;
    
    
    private LocalDateTime publicationDate;

    public Comment() {
    }

    public Comment(String text, LocalDateTime publicationDate) {
        this.text = text;
        this.publicationDate = publicationDate;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public User getCommentUser() {
        return userComment;
    }

    public void setCommentUser(User userComment) {
        this.userComment = userComment;
    }

    public Post getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(Post commentPost) {
        this.commentPost = commentPost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
