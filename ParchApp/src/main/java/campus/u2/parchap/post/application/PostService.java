/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.post.application;

import campus.u2.parchap.post.domain.Post;
import java.util.List;

/**
 *
 * @author kevin
 */
public interface PostService {
    List<Post> getAllTypeMaintenance();

    Post getTypeMaintenanceById(Long id);

    Post saveTypeMaintenance(Post post);

    void deleteTypeMaintenance(Long id);
}
