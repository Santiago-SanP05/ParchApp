/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.like.infrastructure;

import campus.u2.parchap.like.application.ReactionServiceImpl;
import campus.u2.parchap.like.domain.Reaction;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("api/reaction")
public class ReactionController {

    private final ReactionServiceImpl reactionServiceImpl;

    @Autowired
    public ReactionController(ReactionServiceImpl reactionServiceImpl) {
        this.reactionServiceImpl = reactionServiceImpl;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Reaction> findAllPost() {
        return reactionServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional getPostById(@PathVariable Long id) {
        return reactionServiceImpl.findById(id);
    }

    @PostMapping
    public Reaction createPost(@RequestBody Reaction reaction) {
        return reactionServiceImpl.save(reaction);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        reactionServiceImpl.deleteById(id);
    }

    @PutMapping("/{id}")
    public Reaction updateComment(@PathVariable Long id, @RequestBody Reaction reaction) {
        reaction.setIdLike(id);
        return reactionServiceImpl.save(reaction);
    }

}
