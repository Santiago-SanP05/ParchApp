package campus.u2.parchap.like.application;

import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.like.domain.ReactionDTO;
import campus.u2.parchap.like.domain.ReactionRepository;
import campus.u2.parchap.post.application.PostServiceImpl;
import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.post.domain.PostRepository;
import campus.u2.parchap.user.domain.User;
import campus.u2.parchap.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactionServiceImpl {

    private final ReactionRepository reactionRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReactionServiceImpl(ReactionRepository reactionRepository, UserRepository userRepository,PostRepository postRepository) {
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Obtener todas las reacciones
    public List<ReactionDTO> findAll() {
        return reactionRepository.findAll().stream()
                .map(this::convertToDTO) // Convertir a DTO al devolver
                .collect(Collectors.toList());
    }

    // Guardar una nueva reacción
    public ReactionDTO save(ReactionDTO reactionDTO) {
        Reaction reaction = convertToEntity(reactionDTO);

        // Establecer la fecha de publicación si no está configurada
        if (reaction.getPublication_date() == null) {
            reaction.setPublication_date(LocalDateTime.now());
        }

        // Verificar que el usuario asociado no sea nulo
        if (reactionDTO.getIdUser() == null) {
            throw new IllegalArgumentException("El usuario que realiza la reacción no puede ser nulo.");
        }

        // Obtener el usuario a partir del ID del DTO
        User user = userRepository.findById(reactionDTO.getIdUser())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un usuario con el id: " + reactionDTO.getIdUser()));

        // Establecer el usuario en la reacción
        reaction.setLikeUser(user);

        // Guardar la reacción
        Reaction savedReaction = reactionRepository.save(reaction);
        return convertToDTO(savedReaction);
    }

    // Obtener una reacción por su ID
    public Optional<ReactionDTO> findById(Long id) {
        return reactionRepository.findById(id)
                .map(this::convertToDTO); // Convertir entidad a DTO
    }

    // Eliminar una reacción por su ID
    public void deleteById(Long id) {
        reactionRepository.deleteById(id);
    }

    // Convertir entidad Reaction a DTO
    private ReactionDTO convertToDTO(Reaction reaction) {
    return new ReactionDTO(
            reaction.getIdLike(),                             // ID de la reacción
            reaction.getLikeUser().getId_User(),                  // ID del usuario que hizo la reacción
            reaction.getLikePost().getIdPost(),                   // ID del post al que pertenece la reacción
            reaction.getPublication_date()                    // Fecha de publicación de la reacción
    );
}

    // Convertir DTO a entidad Reaction
    private Reaction convertToEntity(ReactionDTO reactionDTO) {
    Reaction reaction = new Reaction();
    reaction.setIdLike(reactionDTO.getIdLike());
    reaction.setPublication_date(reactionDTO.getPublicationDate());

    // Obtener el usuario a partir del ID del DTO
    User user = userRepository.findById(reactionDTO.getIdUser())
            .orElseThrow(() -> new IllegalArgumentException("No se encontró un usuario con el id: " + reactionDTO.getIdUser()));
    reaction.setLikeUser(user);

    // Obtener el post directamente utilizando el PostRepository
    Post post = postRepository.findById(reactionDTO.getIdPost())  // Usamos el PostRepository
            .orElseThrow(() -> new IllegalArgumentException("No se encontró un post con el id: " + reactionDTO.getIdPost()));
    reaction.setLikePost(post);

    return reaction;
}

}

