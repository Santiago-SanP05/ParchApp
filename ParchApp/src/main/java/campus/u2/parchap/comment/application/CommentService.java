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
    List<Comment> getAllTypeMaintenance();

    Comment getTypeMaintenanceById(Long id);

    Comment saveTypeMaintenance(Comment comment);

    void deleteTypeMaintenance(Long id);
}
