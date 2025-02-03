package campus.u2.parchap.comment.domain;

import java.time.LocalDateTime;

public class CommentDTO {
    
    private Long idComment;
    private String text;
    private LocalDateTime publicationDate;

    public CommentDTO(Long idComment, String text, LocalDateTime publicationDate) {
        this.idComment = idComment;
        this.text = text;
        this.publicationDate = publicationDate;
    }
    
    public CommentDTO( String text, LocalDateTime publicationDate) {
        this.text = text;
        this.publicationDate = publicationDate;
    }
    
    public CommentDTO( ) {
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
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
