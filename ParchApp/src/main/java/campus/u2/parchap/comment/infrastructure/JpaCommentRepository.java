/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.comment.infrastructure;

import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.comment.domain.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kevin
 */
public interface JpaCommentRepository extends JpaRepository<Comment, Long>, CommentRepository{
    
}
