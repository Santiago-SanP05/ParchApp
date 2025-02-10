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
        Post post = convertToEntity(postDTO);

        if (post.getPublicationDate() == null) {
            post.setPublicationDate(LocalDateTime.now());
        }

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
    return new PostDTO(
            post.getIdPost(),  
            post.getImageUrl(),  
            post.getPublicationDate(),  
            post.getCaption(),  
            post.getComments().stream()
                    .map(comment -> new CommentDTO(
                            comment.getIdComment(),
                            comment.getText(),
                            comment.getPublicationDate(),
                            comment.getCommentUser().getId_User(),
                            comment.getCommentPost().getIdPost()))
                    .collect(Collectors.toList()),
            post.getLike1().stream()  
                    .map(reaction -> new ReactionDTO(
                            reaction.getIdLike(),  
                            reaction.getLikeUser().getId_User(),  
                            reaction.getLikePost().getIdPost(),  
                            reaction.getPublication_date()))  
                    .collect(Collectors.toList()),
            post.getUserPublication().getId_User()  
    );
}

    public List<PostDTO> getPostsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return user.getPost().stream()
                .map(post -> new PostDTO(
                post.getIdPost(), 
                post.getImageUrl(), 
                post.getPublicationDate(), 
                post.getCaption(), 
                post.getComments().stream()     
                        .map(comment -> new CommentDTO(
                        comment.getIdComment(),
                        comment.getText(),
                        comment.getPublicationDate(),
                        comment.getCommentUser().getId_User(), 
                        comment.getCommentPost().getIdPost())) 
                        .collect(Collectors.toList()),
                // Convertir reacciones a DTOs
                post.getLike1().stream()
                        .map(reaction -> new ReactionDTO(
                        reaction.getIdLike(),       
                        reaction.getLikeUser().getId_User(),        
                        reaction.getLikePost().getIdPost(), 
                        reaction.getPublication_date()))      
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
