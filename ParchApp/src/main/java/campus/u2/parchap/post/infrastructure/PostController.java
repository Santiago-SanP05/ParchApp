package campus.u2.parchap.post.infrastructure;

import campus.u2.parchap.post.application.PostServiceImpl;
import campus.u2.parchap.post.domain.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/post")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    @Autowired
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getAllPost() {
        return postServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PostDTO> getPostById(@PathVariable Long id) {
        return postServiceImpl.findById(id);
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postServiceImpl.save(postDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postServiceImpl.deleteById(id);
    }

    @PutMapping("/{id}")
    public PostDTO updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        postDTO.setIdPost(id);
        return postServiceImpl.save(postDTO);
    }
}

