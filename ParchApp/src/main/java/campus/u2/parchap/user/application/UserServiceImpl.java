package campus.u2.parchap.user.application;

import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.comment.domain.CommentDTO;
import campus.u2.parchap.comment.domain.CommentRepository;
import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.follower.domain.FollowerDTO;
import campus.u2.parchap.follower.domain.FollowerRepository;
import campus.u2.parchap.like.domain.ReactionDTO;
import campus.u2.parchap.post.domain.PostDTO;
import campus.u2.parchap.user.domain.User;
import campus.u2.parchap.user.domain.UserDTO;
import campus.u2.parchap.user.domain.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    private final CommentRepository commentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FollowerRepository followerRepository, campus.u2.parchap.comment.domain.CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
        this.commentRepository = commentRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    public UserDTO save(UserDTO userDTO) {
        User user;

        if (userDTO.getIdUser() != null) {
            user = userRepository.findById(userDTO.getIdUser()).orElse(new User());
        } else {
            user = new User();
            user.setCreateDate(LocalDateTime.now());
        }

        user.setName(userDTO.getName());
        user.setNameUser(userDTO.getNameUser());
        user.setEmail(userDTO.getEmail());
        user.setBiography(userDTO.getBiography());
        user.setUrlPhoto(userDTO.getUrlPhoto());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        user.setUpdateDate(LocalDateTime.now());

        user = userRepository.save(user);

        return new UserDTO(user.getId_User(), user.getName(), user.getNameUser(), user.getEmail(), user.getBiography(), user.getUrlPhoto());
    }

    @Transactional
    public void deleteUser(Long userId) {
        List<Comment> comments = commentRepository.findAll()
                .stream()
                .filter(comment -> comment.getCommentUser().getId_User().equals(userId))
                .collect(Collectors.toList());

        for (Comment comment : comments) {
            commentRepository.delete(comment);
        }

        followerRepository.deleteByUserFollower(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        followerRepository.deleteByUserFollowed(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        userRepository.deleteById(userId);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getId_User());
        userDTO.setName(user.getName());
        userDTO.setNameUser(user.getNameUser());
        userDTO.setUrlPhoto(user.getUrlPhoto());
        userDTO.setEmail(user.getEmail());
        userDTO.setBiography(user.getBiography());
        return userDTO;
    }

    public void followUser(Long followerId, Long followedId) {
        if (followerId.equals(followedId)) {
            throw new IllegalArgumentException("Un usuario no puede seguirse a sí mismo.");
        }

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Seguidor no encontrado"));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new RuntimeException("Usuario seguido no encontrado"));

        // Verificar si ya existe la relación de seguimiento
        Optional<Follower> existingFollow = followerRepository.findByUserFollower_IdUserAndUserFollowed_IdUser(followerId, followedId);
        if (existingFollow.isPresent()) {
            throw new IllegalArgumentException("Ya estás siguiendo a este usuario.");
        }

        Follower follow = new Follower();
        follow.setUserFollower(follower);
        follow.setUserFollowed(followed);
        follow.setFollowDate(LocalDateTime.now());

        followerRepository.save(follow);
    }

    public List<PostDTO> getPostsByUserId(Long userId) {
        // Buscar al usuario por su ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener los posts del usuario
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

    public List<UserDTO> getFollowersByUserId(Long userId) {
        // Obtener el usuario desde la base de datos
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Follower> followers = followerRepository.findByUserFollowed(user);

        Set<Long> seenUsers = new HashSet<>();

        return followers.stream()
                .filter(follower -> seenUsers.add(follower.getUserFollower().getId_User())) // Evitar duplicados
                .map(follower -> convertToDTO(follower.getUserFollower()))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getFollowedByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Follower> followed = followerRepository.findByUserFollower(user);

        Set<Long> seenUsers = new HashSet<>();

        return followed.stream()
                .filter(follower -> seenUsers.add(follower.getUserFollowed().getId_User()))
                .map(follower -> convertToDTO(follower.getUserFollowed()))
                .collect(Collectors.toList());
    }

//    public List<FollowerDTO> getFollowersByUserId(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//        return user.getFollower().stream()
//                .map(follower -> new FollowerDTO(
//                follower.getIdFollower(),
//                follower.getFollowDate(),
//                follower.getUserFollower().getId_User(),
//                follower.getUserFollowed().getId_User()))
//                .collect(Collectors.toList());
//    }
//
//    public List<FollowerDTO> getFollowedByUserId(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//        return user.getFollowed().stream()
//                .map(followed -> new FollowerDTO(
//                followed.getIdFollower(),
//                followed.getFollowDate(),
//                followed.getUserFollower().getId_User(),
//                followed.getUserFollowed().getId_User()))
//                .collect(Collectors.toList());
//    }
    
    
    public UserDTO updateBiography(Long userId, String newBiography) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setBiography(newBiography);
        user.setUpdateDate(LocalDateTime.now());
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    public UserDTO changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdateDate(LocalDateTime.now());
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    public void unfollowUser(Long followerId, Long followedId) {
        Follower follow = followerRepository.findByUserFollower_IdUserAndUserFollowed_IdUser(followerId, followedId)
                .orElseThrow(() -> new RuntimeException("No existe una relación de seguimiento"));

        followerRepository.delete(follow);
    }
    
    public UserDTO findByNameUser(String nameUser) {
    User user = userRepository.findByNameUser(nameUser)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    return new UserDTO(user.getId_User(),user.getName(),user.getNameUser(),user.getEmail(),user.getBiography(),user.getUrlPhoto());
}

}
