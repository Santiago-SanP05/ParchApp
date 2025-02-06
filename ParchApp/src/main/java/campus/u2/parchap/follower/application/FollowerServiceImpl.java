package campus.u2.parchap.follower.application;

import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.follower.domain.FollowerDTO;
import campus.u2.parchap.follower.domain.FollowerRepository;
import campus.u2.parchap.user.domain.User;
import campus.u2.parchap.user.domain.UserRepository;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FollowerServiceImpl {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Autowired
    public FollowerServiceImpl(FollowerRepository followerRepository, UserRepository userRepository) {
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    public List<FollowerDTO> findAll() {
    List<Follower> followers = followerRepository.findAll();
    return followers.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
}

    public FollowerDTO save(FollowerDTO followerDTO) {
        // Convertimos el DTO a entidad
        Follower follower = convertToEntity(followerDTO);

        // Verificamos si ya existe una relación entre los usuarios
        Optional<Follower> existingFollower = followerRepository.findByUserFollowerAndUserFollowed(
                follower.getUserFollower(), follower.getUserFollowed()
        );

        // Si ya existe, lanzamos un error
        if (existingFollower.isPresent()) {
            throw new IllegalArgumentException("Esta relación de seguimiento ya existe.");
        }

        // Si no existe, lo guardamos
        Follower savedFollower = followerRepository.save(follower);
        return convertToDTO(savedFollower);
    }

    public Optional<FollowerDTO> findById(Long id) {
        return followerRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void deleteById(Long id) {
        followerRepository.deleteById(id);
    }

    private FollowerDTO convertToDTO(Follower follower) {
        Long userFollowerId = follower.getUserFollower().getId_User();
        Long userFollowedId = follower.getUserFollowed().getId_User();
        
        return new FollowerDTO(
                follower.getIdFollower(),
                follower.getFollowDate(),
                userFollowerId,  
                userFollowedId   
        );
    }

    private Follower convertToEntity(FollowerDTO followerDTO) {
        Follower follower = new Follower();
        follower.setFollowDate(followerDTO.getFollowDate());

        User userFollower = userRepository.findById(followerDTO.getUserFollowerId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + followerDTO.getUserFollowerId()));

        User userFollowed = userRepository.findById(followerDTO.getUserFollowedId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + followerDTO.getUserFollowedId()));

        follower.setUserFollower(userFollower);
        follower.setUserFollowed(userFollowed);

        return follower;
    }
}

