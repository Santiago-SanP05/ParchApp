package campus.u2.parchap.like.domain;

import java.time.LocalDateTime;

public class ReactionDTO {

    private Long idLike; // ID de la reacción
    private Long idUser; // ID del usuario que hace la reacción
    private Long idPost; // ID del post en el que se hace la reacción
    private LocalDateTime publicationDate; // Fecha de publicación de la reacción

    // Constructor con todos los parámetros
    public ReactionDTO(Long idLike, Long idUser, Long idPost, LocalDateTime publicationDate) {
        this.idLike = idLike;
        this.idUser = idUser;
        this.idPost = idPost;
        this.publicationDate = publicationDate;
    }

    // Constructor con solo fecha de publicación
    public ReactionDTO(Long idUser, Long idPost, LocalDateTime publicationDate) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.publicationDate = publicationDate;
    }

    // Constructor vacío (por si necesitas crear un objeto vacío)
    public ReactionDTO() {
    }

    // Getters y setters
    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
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

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
