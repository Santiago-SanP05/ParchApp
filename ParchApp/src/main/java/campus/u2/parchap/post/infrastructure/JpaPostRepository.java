/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.post.infrastructure;

import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.post.domain.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kevin
 */
@Repository
public interface JpaPostRepository extends JpaRepository<Post, Long>, PostRepository{
    
}
