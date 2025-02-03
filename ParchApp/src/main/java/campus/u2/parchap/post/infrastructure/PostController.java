package campus.u2.parchap.post.infrastructure;

import campus.u2.parchap.post.application.PostServiceImpl;
import campus.u2.parchap.post.domain.PostDTO;
import campus.u2.parchap.post.domain.Post;
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

    // Método para obtener todos los posts
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getAllPost() {
        return postServiceImpl.findAll();
    }

    // Método para obtener un post por ID
    @GetMapping("/{id}")
    public Optional<PostDTO> getPostById(@PathVariable Long id) {
        return postServiceImpl.findById(id);
    }

    // Método para crear un nuevo post
    @PostMapping
    public PostDTO createPost(@RequestBody Post post) {
        return postServiceImpl.save(post);
    }

    // Método para eliminar un post
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postServiceImpl.deleteById(id);
    }

    // Método para actualizar un post
    @PutMapping("/{id}")
    public PostDTO updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setIdPost(id);
        return postServiceImpl.save(post);
    }
}
