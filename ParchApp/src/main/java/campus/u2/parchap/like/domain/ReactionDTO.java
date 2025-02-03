package campus.u2.parchap.like.domain;

import java.time.LocalDateTime;


public class ReactionDTO {
    
    private Long idLike;
    private LocalDateTime publication_date;

    public ReactionDTO(Long idLike, LocalDateTime publication_date) {
        this.idLike = idLike;
        this.publication_date = publication_date;
    }
    
    public ReactionDTO( LocalDateTime publication_date) {
        this.publication_date = publication_date;
    }
    
    public ReactionDTO() {
    }

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public LocalDateTime getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(LocalDateTime publication_date) {
        this.publication_date = publication_date;
    }
    
    
    
    
    
    
    
    
}
