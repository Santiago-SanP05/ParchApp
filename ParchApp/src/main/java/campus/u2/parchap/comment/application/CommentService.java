/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.comment.application;

import campus.u2.parchap.comment.domain.Comment;
import java.util.List;

/**
 *
 * @author kevin
 */

public interface CommentService {
    List<Comment> getAllComment();

    Comment getTypeCommentById(Long id);

    Comment saveTypeComment(Comment comment);

    void deleteTypeComment(Long id);
}
