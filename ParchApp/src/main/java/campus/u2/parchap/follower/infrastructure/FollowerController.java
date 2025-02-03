package campus.u2.parchap.follower.infrastructure;

import campus.u2.parchap.follower.application.FollowerServiceImpl;
import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.follower.domain.FollowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/follower")
public class FollowerController {

    private final FollowerServiceImpl followerServiceImpl;

    @Autowired
    public FollowerController(FollowerServiceImpl followerServiceImpl) {
        this.followerServiceImpl = followerServiceImpl;
    }

    // Obtener todos los seguidores
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FollowerDTO> getAllFollowers() {
        return followerServiceImpl.findAll();
    }

    // Obtener un seguidor por ID
    @GetMapping("/{id}")
    public ResponseEntity<FollowerDTO> getFollowerById(@PathVariable Long id) {
        Optional<FollowerDTO> followerDTO = followerServiceImpl.findById(id);
        if (followerDTO.isPresent()) {
            return ResponseEntity.ok(followerDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Crear un nuevo seguidor
    @PostMapping
    public ResponseEntity<FollowerDTO> createFollower(@RequestBody Follower follower) {
        FollowerDTO savedFollower = followerServiceImpl.save(follower);  // Recibe el objeto Follower directamente
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFollower);
    }

    // Eliminar un seguidor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollower(@PathVariable Long id) {
        Optional<FollowerDTO> followerDTO = followerServiceImpl.findById(id);
        if (followerDTO.isPresent()) {
            followerServiceImpl.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Actualizar un seguidor
    @PutMapping("/{id}")
    public ResponseEntity<FollowerDTO> updateFollower(@PathVariable Long id, @RequestBody Follower follower) {
        if (!followerServiceImpl.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        follower.setIdFollower(id);  // Seteamos el ID del seguidor a actualizar
        FollowerDTO updatedFollower = followerServiceImpl.save(follower);
        return ResponseEntity.ok(updatedFollower);
    }
}
