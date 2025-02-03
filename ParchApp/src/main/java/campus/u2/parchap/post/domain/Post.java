package campus.u2.parchap.post.domain;

import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User userPublication;
    
    private String caption;
    
    private String imageUrl;
    
    
    private LocalDateTime publicationDate;
    
    @OneToMany(mappedBy = "commentPost")
    private List<Comment> comment1 = new ArrayList<>();
    
    @OneToMany(mappedBy = "likePost")
    private List<Reaction> like1 = new ArrayList<>();
    
    public Post() {
    }

    public Post(String caption, String imageUrl, LocalDateTime publicationDate) {
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

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
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
    
    public void addLike1(Reaction like) {
        this.like1.add(like);
        like.setLikePost(this);
        
    }
    
    public void removeLike1(Reaction like) {
        this.like1.remove(like);
        like.setLikePost(this);
    }

    public List<Reaction> getLike1() {
        return like1;
    }
    
}
