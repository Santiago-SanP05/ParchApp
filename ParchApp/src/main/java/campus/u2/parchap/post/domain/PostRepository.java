package campus.u2.parchap.post.domain;

import java.util.List;
import java.util.Optional;



public interface PostRepository {
    List<Post> findAll();
    Post save(Post post);
    Optional <Post> findById(Long id);
    void deleteById(Long Id);
}
