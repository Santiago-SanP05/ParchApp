package campus.u2.parchap.user.infrastructure;

import campus.u2.parchap.follower.domain.FollowerDTO;
import campus.u2.parchap.post.domain.PostDTO;
import campus.u2.parchap.user.application.UserServiceImpl;
import campus.u2.parchap.user.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userServiceImpl.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userServiceImpl.findById(id);
        return userDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable Long id) {
        List<PostDTO> posts = userServiceImpl.getPostsByUserId(id);
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userServiceImpl.save(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setIdUser(id);
        UserDTO updatedUser = userServiceImpl.save(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.noContent().build();  // Retorna 204 No Content si todo fue exitoso
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<UserDTO>> getFollowers(@PathVariable Long id) {
        List<UserDTO> followers = userServiceImpl.getFollowersByUserId(id);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{id}/followed")
    public ResponseEntity<List<UserDTO>> getFollowed(@PathVariable Long id) {
        List<UserDTO> followed = userServiceImpl.getFollowedByUserId(id);
        return ResponseEntity.ok(followed);
    }
    
    @GetMapping("/user/{nameUser}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String nameUser) {
        return ResponseEntity.ok(userServiceImpl.findByNameUser(nameUser));
    }
}
