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
        return postRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
        PostDTO postDTO = new PostDTO();
        postDTO.setIdPost(post.getIdPost());
        postDTO.setImageUrl(post.getImageUrl());
        postDTO.setPublicationDate(post.getPublicationDate());
        postDTO.setUserId(post.getUserPublication().getId_User());
        
        List<CommentDTO> commentDTOs = post.getComments().stream()
                .map(comment -> new CommentDTO(comment.getIdComment(), comment.getText(), comment.getPublicationDate()))
                .collect(Collectors.toList());

        List<ReactionDTO> reactionDTOs = post.getLike1().stream()
                .map(reaction -> new ReactionDTO(reaction.getIdLike(), reaction.getPublication_date()))
                .collect(Collectors.toList());
        
        postDTO.setComents(commentDTOs);
        postDTO.setReactions(reactionDTOs);
        return postDTO;
    }

    private Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setIdPost(postDTO.getIdPost());
        post.setImageUrl(postDTO.getImageUrl());
        post.setPublicationDate(postDTO.getPublicationDate());
        
        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUserPublication(user);
        
        return post;
    }
}
