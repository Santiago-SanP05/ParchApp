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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FollowerDTO> getAllFollowers() {
        return followerServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowerDTO> getFollowerById(@PathVariable Long id) {
        Optional<FollowerDTO> followerDTO = followerServiceImpl.findById(id);
        return followerDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<FollowerDTO> createFollower(@RequestBody FollowerDTO followerDTO) {
        FollowerDTO savedFollower = followerServiceImpl.save(followerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFollower);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollower(@PathVariable Long id) {
        if (followerServiceImpl.findById(id).isPresent()) {
            followerServiceImpl.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FollowerDTO> updateFollower(@PathVariable Long id, @RequestBody FollowerDTO followerDTO) {
        if (!followerServiceImpl.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        followerDTO.setIdFollower(id);  // Seteamos el ID del seguidor a actualizar
        FollowerDTO updatedFollower = followerServiceImpl.save(followerDTO);
        return ResponseEntity.ok(updatedFollower);
    }
}

