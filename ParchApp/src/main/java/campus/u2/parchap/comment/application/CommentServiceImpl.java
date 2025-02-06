package campus.u2.parchap.comment.application;

import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.comment.domain.CommentDTO;
import campus.u2.parchap.comment.domain.CommentRepository;
import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.user.domain.UserRepository;
import campus.u2.parchap.post.domain.PostRepository;
import campus.u2.parchap.user.domain.User;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;  // Para manejar usuarios
    private final PostRepository postRepository;  // Para manejar publicaciones

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
            UserRepository userRepository,
            PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Obtener todos los comentarios
    public List<CommentDTO> findAll() {
        return commentRepository.findAll().stream()
                .map(this::convertToDTO) // Convertir cada comentario a DTO
                .collect(Collectors.toList());
    }

    // Buscar un comentario por ID
    public Optional<CommentDTO> findById(Long id) {
        return commentRepository.findById(id)
                .map(this::convertToDTO);  // Convertir a DTO si se encuentra
    }

    // Guardar un comentario
    public CommentDTO save(CommentDTO commentDTO) {
    Comment comment;

    if (commentDTO.getIdComment() != null) {
        // Verificar si el comentario ya existe en la base de datos
        Optional<Comment> existingComment = commentRepository.findById(commentDTO.getIdComment());
        if (existingComment.isPresent()) {
            // Si existe, actualizamos los campos
            comment = existingComment.get();
            comment.setText(commentDTO.getText());
            comment.setPublicationDate(LocalDateTime.now());
        } else {
            // Si no existe, creamos un nuevo comentario
            comment = convertToEntity(commentDTO);
            if (comment.getPublicationDate() == null) {
                comment.setPublicationDate(LocalDateTime.now());
            }
        }
    } else {
        // Si no tiene ID, es un nuevo comentario
        comment = convertToEntity(commentDTO);
        if (comment.getPublicationDate() == null) {
            comment.setPublicationDate(LocalDateTime.now());
        }
    }

    // Guardamos el comentario en la BD
    Comment savedComment = commentRepository.save(comment);

    return convertToDTO(savedComment);
}

    // Eliminar un comentario por ID
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    // Convertir de entidad a DTO
    private CommentDTO convertToDTO(Comment comment) {
        return new CommentDTO(
                comment.getIdComment(),
                comment.getText(),
                comment.getPublicationDate(),
                comment.getCommentUser().getId_User(), // Aquí corregimos el acceso a idUser
                comment.getCommentPost().getIdPost()
        );
    }

    // Convertir de DTO a entidad
    private Comment convertToEntity(CommentDTO commentDTO) {
        // Crear entidad Comment a partir de los datos del DTO
        Comment comment = new Comment(commentDTO.getText(), commentDTO.getPublicationDate());

        // Obtener usuario y publicación usando los ID proporcionados en el DTO
        User user = userRepository.findById(commentDTO.getIdUser()) // Usamos el ID del usuario
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = postRepository.findById(commentDTO.getIdPost()) // Usamos el ID de la publicación
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // Asociar usuario y publicación al comentario
        comment.setCommentUser(user);
        comment.setCommentPost(post);

        return comment;
    }
}
