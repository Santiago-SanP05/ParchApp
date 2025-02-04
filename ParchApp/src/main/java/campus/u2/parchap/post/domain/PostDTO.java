package campus.u2.parchap.post.domain;

import campus.u2.parchap.comment.domain.CommentDTO;
import campus.u2.parchap.like.domain.ReactionDTO;
import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {
    
    private Long idPost;
    private String imageUrl;
    private LocalDateTime publicationDate;
    private String caption;
    private List<CommentDTO> coments;
    private List<ReactionDTO> reactions;
    private Long userId;  

    public PostDTO(Long idPost, String imageUrl, LocalDateTime publicationDate,String caption, List<CommentDTO> coments, List<ReactionDTO> reactions, Long userId) {
        this.idPost = idPost;
        this.imageUrl = imageUrl;
        this.publicationDate = publicationDate;
        this.caption = caption;
        this.coments = coments;
        this.reactions = reactions;
        this.userId = userId;
    }
    
    public PostDTO(String imageUrl, LocalDateTime publicationDate,String caption, List<CommentDTO> coments, List<ReactionDTO> reactions, Long userId) {
        this.imageUrl = imageUrl;
        this.publicationDate = publicationDate;
        this.caption = caption;
        this.coments = coments;
        this.reactions = reactions;
        this.userId = userId;
    }
    
    public PostDTO(){}

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
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

    public List<CommentDTO> getComents() {
        return coments;
    }

    public void setComents(List<CommentDTO> coments) {
        this.coments = coments;
    }

    public List<ReactionDTO> getReactions() {
        return reactions;
    }

    public void setReactions(List<ReactionDTO> reactions) {
        this.reactions = reactions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    
    
}
