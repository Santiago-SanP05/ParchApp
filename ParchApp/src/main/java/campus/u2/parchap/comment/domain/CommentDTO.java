package campus.u2.parchap.comment.domain;

import java.time.LocalDateTime;

public class CommentDTO {
    
    private Long idComment;
    private String text;
    private LocalDateTime publicationDate;
    private Long idUser;  // ID del usuario que hizo el comentario
    private Long idPost;  // ID del post donde se hizo el comentario

    public CommentDTO(Long idComment, String text, LocalDateTime publicationDate, Long idUser, Long idPost) {
        this.idComment = idComment;
        this.text = text;
        this.publicationDate = publicationDate;
        this.idUser = idUser;
        this.idPost = idPost;
    }
    
    public CommentDTO(String text, LocalDateTime publicationDate, Long idUser, Long idPost) {
        this.text = text;
        this.publicationDate = publicationDate;
        this.idUser = idUser;
        this.idPost = idPost;
    }
    
    public CommentDTO(Long idComment, String text, LocalDateTime publicationDate) {
        this.idComment = idComment;
        this.text = text;
        this.publicationDate = publicationDate;
    }
    
    public CommentDTO() {
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }
}
