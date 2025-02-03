package campus.u2.parchap.comment.application;

import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.comment.domain.CommentDTO;
import campus.u2.parchap.comment.domain.CommentRepository;
import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.user.domain.UserRepository;
import campus.u2.parchap.post.domain.PostRepository;
import campus.u2.parchap.user.domain.User;
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
                .map(this::convertToDTO)  // Convertir cada comentario a DTO
                .collect(Collectors.toList());
    }

    // Buscar un comentario por ID
    public Optional<CommentDTO> findById(Long id) {
        return commentRepository.findById(id)
                .map(this::convertToDTO);  // Convertir a DTO si se encuentra
    }

    // Guardar un comentario
    public CommentDTO save(Comment comment) {
        Comment savedComment = commentRepository.save(comment);  // Guardamos directamente la entidad
        return convertToDTO(savedComment);  // Convertimos la entidad guardada a DTO
    }

    // Eliminar un comentario por ID
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    // Convertir de entidad a DTO
    private CommentDTO convertToDTO(Comment comment) {
        return new CommentDTO(comment.getIdComment(), comment.getText(), comment.getPublicationDate());
    }

    // Convertir de DTO a entidad
    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment(commentDTO.getText(), commentDTO.getPublicationDate());

        // Recibir el ID del usuario y la publicación desde el DTO
        User user = userRepository.findById(commentDTO.getIdComment())  // Asignar usuario al comentario
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        Post post = postRepository.findById(commentDTO.getIdComment())  // Asignar publicación al comentario
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        comment.setCommentUser(user);
        comment.setCommentPost(post);

        return comment;
    }
}
