package campus.u2.parchap.post.application;

import campus.u2.parchap.comment.domain.CommentDTO;
import campus.u2.parchap.like.domain.ReactionDTO;
import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.post.domain.PostDTO;
import campus.u2.parchap.post.domain.PostRepository;
import campus.u2.parchap.user.domain.User;
import campus.u2.parchap.user.domain.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostDTO> findAll() {
    return postRepository.findAll().stream()  // Obtenemos todos los posts de la base de datos
            .map(this::convertToDTO)  // Convertimos cada Post a un PostDTO usando el método convertToDTO
            .collect(Collectors.toList());  // Recogemos los DTOs en una lista
}


    public PostDTO save(PostDTO postDTO) {
        // Convertir DTO a entidad Post
        Post post = convertToEntity(postDTO);

        // Establecer fecha de publicación si no está definida
        if (post.getPublicationDate() == null) {
            post.setPublicationDate(LocalDateTime.now());
        }

        // Guardar el post en la base de datos
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    public Optional<PostDTO> findById(Long id) {
        return postRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
    
    private PostDTO convertToDTO(Post post) {
    // Convertir la entidad Post a un PostDTO
    return new PostDTO(
            post.getIdPost(),  // ID del post
            post.getImageUrl(),  // URL de la imagen
            post.getPublicationDate(),  // Fecha de publicación
            post.getCaption(),  // Descripción del post
            post.getComments().stream()  // Convertir comentarios a DTOs
                    .map(comment -> new CommentDTO(
                            comment.getIdComment(),
                            comment.getText(),
                            comment.getPublicationDate(),
                            comment.getCommentUser().getId_User(),  // ID del usuario que hizo el comentario
                            comment.getCommentPost().getIdPost()))  // ID del post comentado
                    .collect(Collectors.toList()),
            post.getLike1().stream()  // Convertir reacciones a DTOs
                    .map(reaction -> new ReactionDTO(
                            reaction.getIdLike(),  // ID de la reacción
                            reaction.getLikeUser().getId_User(),  // ID del usuario que hizo la reacción
                            reaction.getLikePost().getIdPost(),  // ID del post en el que se hizo la reacción
                            reaction.getPublication_date()))  // Fecha de publicación de la reacción
                    .collect(Collectors.toList()),
            post.getUserPublication().getId_User()  // ID del usuario que publicó el post
    );
}

    public List<PostDTO> getPostsByUserId(Long userId) {
        // Buscar al usuario por su ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener los posts del usuario y mapear a PostDTO
        return user.getPost().stream()
                .map(post -> new PostDTO(
                post.getIdPost(), // ID del post
                post.getImageUrl(), // URL de la imagen
                post.getPublicationDate(), // Fecha de publicación
                post.getCaption(), // Descripción del post
                post.getComments().stream() // Convertir comentarios a DTOs
                        .map(comment -> new CommentDTO(
                        comment.getIdComment(),
                        comment.getText(),
                        comment.getPublicationDate(),
                        comment.getCommentUser().getId_User(), // ID del usuario que hizo el comentario
                        comment.getCommentPost().getIdPost())) // ID del post comentado
                        .collect(Collectors.toList()),
                // Convertir reacciones a DTOs
                post.getLike1().stream()
                        .map(reaction -> new ReactionDTO(
                        reaction.getIdLike(), // ID de la reacción
                        reaction.getLikeUser().getId_User(), // ID del usuario que hizo la reacción
                        reaction.getLikePost().getIdPost(), // ID del post en el que se hizo la reacción
                        reaction.getPublication_date())) // Fecha de publicación de la reacción
                        .collect(Collectors.toList()),
                // ID del usuario que publicó el post
                post.getUserPublication().getId_User()))
                .collect(Collectors.toList());
    }

    private Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setIdPost(postDTO.getIdPost());
        post.setCaption(postDTO.getCaption());
        post.setImageUrl(postDTO.getImageUrl());
        post.setPublicationDate(postDTO.getPublicationDate());

        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUserPublication(user);

        return post;
    }
}
