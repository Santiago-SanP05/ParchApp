/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.follower.infrastructure;

import campus.u2.parchap.follower.application.FollowerServiceImpl;
import campus.u2.parchap.follower.domain.Follower;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kevin
 */
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
    public List<Follower> getAllPost() {
        return followerServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional getPostById(@PathVariable Long id) {
        return followerServiceImpl.findById(id);
    }

    @PostMapping
    public Follower createPost(@RequestBody Follower follower) {
        return followerServiceImpl.save(follower);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        followerServiceImpl.deleteById(id);
    }

    @PutMapping("/{id}")
    public Follower updateComment(@PathVariable Long id, @RequestBody Follower follower) {
        follower.setIdFollower(id);
        return followerServiceImpl.save(follower);
    }

}
