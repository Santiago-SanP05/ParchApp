/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.post.domain;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin
 */

public interface PostRepository {
    List<Post> findAll();
    Post save(Post post);
    Optional <Post> findById(Long id);
    void deleteById(Long Id);
}
