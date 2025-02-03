package campus.u2.parchap.like.application;

import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.like.domain.ReactionDTO;
import campus.u2.parchap.like.domain.ReactionRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public ReactionServiceImpl(ReactionRepository reactionRepository, UserRepository userRepository) {
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
    }

    // Obtener todas las reacciones
    public List<ReactionDTO> findAll() {
        return reactionRepository.findAll().stream()
                .map(this::convertToDTO) // Convertir a DTO al devolver
                .collect(Collectors.toList());
    }

    // Guardar una nueva reacción
    public ReactionDTO save(Reaction reaction) {
        // Establecer la fecha de publicación si no está configurada
        if (reaction.getPublication_date() == null) {
            reaction.setPublication_date(LocalDateTime.now());
        }

        // Verificar que el usuario asociado no sea nulo
        if (reaction.getLikeUser()== null) {
            throw new IllegalArgumentException("El usuario que realiza la reacción no puede ser nulo.");
        }

        // Obtener el usuario a partir del ID, solo si 'userLike' no es nulo
        User user = userRepository.findById(reaction.getLikeUser().getId_User())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un usuario con el id: " + reaction.getLikeUser().getId_User()));

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
        return new ReactionDTO(reaction.getIdLike(), reaction.getPublication_date());
    }

    // Convertir DTO a entidad Reaction
    private Reaction convertToEntity(ReactionDTO reactionDTO) {
        Reaction reaction = new Reaction();
        reaction.setIdLike(reactionDTO.getIdLike());
        reaction.setPublication_date(reactionDTO.getPublication_date());

        // Aquí podrías también convertir el User del DTO, si fuera necesario
        return reaction;
    }
}

