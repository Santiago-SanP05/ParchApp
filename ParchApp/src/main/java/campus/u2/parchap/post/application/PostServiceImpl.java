package campus.u2.parchap.post.application;

import campus.u2.parchap.comment.domain.CommentDTO;
import campus.u2.parchap.like.domain.ReactionDTO;
import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.post.domain.PostDTO;
import campus.u2.parchap.post.domain.PostRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> findAll() {
        return postRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO save(Post post) {
        // Establecer la fecha de publicación si no está asignada
        if (post.getPublicationDate() == null) {
            post.setPublicationDate(LocalDateTime.now());
        }

        // Guardar el post directamente
        Post savedPost = postRepository.save(post);

        // Convertir el post guardado a DTO
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
        PostDTO postDTO = new PostDTO();
        postDTO.setIdPost(post.getIdPost());
        postDTO.setImageUrl(post.getImageUrl());
        postDTO.setPublicationDate(post.getPublicationDate());

        // Convertir los comentarios y reacciones a DTO
        List<CommentDTO> commentDTOs = post.getComments().stream()
                .map(comment -> new CommentDTO(comment.getIdComment(), comment.getText(), comment.getPublicationDate()))
                .collect(Collectors.toList());

        List<ReactionDTO> reactionDTOs = post.getLike1().stream()
                .map(reaction -> new ReactionDTO(reaction.getIdLike(), reaction.getPublication_date()))
                .collect(Collectors.toList());

        postDTO.setComents(commentDTOs);
        postDTO.setReactions(reactionDTOs);
        postDTO.setUserId(post.getUserPublication().getId_User());

        return postDTO;
    }
}
