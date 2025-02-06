/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.comment.domain;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin
 */
public interface CommentRepository {
    List<Comment> findAll();
    Comment save(Comment comment);
    Optional <Comment> findById(Long id);
    void deleteById(Long Id);
    void delete(Comment comment);
}
