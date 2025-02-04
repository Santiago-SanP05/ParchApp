package campus.u2.parchap.user.application;

import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.follower.domain.FollowerRepository;
import campus.u2.parchap.user.domain.User;
import campus.u2.parchap.user.domain.UserDTO;
import campus.u2.parchap.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FollowerRepository followerRepository) {
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
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
        User user = new User();
        user.setName(userDTO.getName());
        user.setNameUser(userDTO.getNameUser());
        user.setEmail(userDTO.getEmail());
        user.setBiography(userDTO.getBiography());
        user.setUrlPhoto(userDTO.getUrlPhoto());

        // Asignar valores adicionales
        user.setPassword(passwordEncoder.encode(userDTO.getPasword())); // Debe venir del frontend
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());

        // Guardar usuario en la base de datos
        user = userRepository.save(user);

        // Retornar un UserDTO sin la contraseña
        return new UserDTO(user.getId_User(), user.getName(), user.getNameUser(), user.getEmail(), user.getBiography(), user.getUrlPhoto());
}


    public void deleteById(Long id) {
        userRepository.deleteById(id);
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

    // Métodos para seguir y dejar de seguir a usuarios (si es necesario)
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

    public void unfollowUser(Long followerId, Long followedId) {
        Follower follow = followerRepository.findByUserFollower_IdUserAndUserFollowed_IdUser(followerId, followedId)
                .orElseThrow(() -> new RuntimeException("No existe una relación de seguimiento"));

        followerRepository.delete(follow);
    }
}

