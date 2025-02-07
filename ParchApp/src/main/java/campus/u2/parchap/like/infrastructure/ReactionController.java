package campus.u2.parchap.like.infrastructure;

import campus.u2.parchap.like.application.ReactionServiceImpl;
import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.like.domain.ReactionDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reaction")
public class ReactionController {

    private final ReactionServiceImpl reactionServiceImpl;

    @Autowired
    public ReactionController(ReactionServiceImpl reactionServiceImpl) {
        this.reactionServiceImpl = reactionServiceImpl;
    }

    // Obtener todas las reacciones
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReactionDTO> findAllReactions() {
        return reactionServiceImpl.findAll();
    }

    // Obtener una reacción por ID
    @GetMapping("/{id}")
    public Optional<ReactionDTO> getReactionById(@PathVariable Long id) {
        return reactionServiceImpl.findById(id);
    }

    // Crear una nueva reacción
    @PostMapping
    public ReactionDTO createReaction(@RequestBody ReactionDTO reactionDTO) {
        return reactionServiceImpl.save(reactionDTO);
    }

    // Eliminar una reacción
    @DeleteMapping("/{id}")
    public void deleteReaction(@PathVariable Long id) {
        reactionServiceImpl.deleteById(id);
    }

    // Actualizar una reacción
    @PutMapping("/{id}")
    public ReactionDTO updateReaction(@PathVariable Long id, @RequestBody ReactionDTO reactionDTO) {
        reactionDTO.setIdLike(id); // Asegurar que el ID del DTO coincide con el de la URL
        return reactionServiceImpl.save(reactionDTO);
    }
}
