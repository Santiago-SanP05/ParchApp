/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.comment.infrastructure;

import campus.u2.parchap.comment.application.CommentServiceImpl;
import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kevin
 */
@RestController
@RequestMapping("api/comment")
public class CommentController {

    
    private final CommentServiceImpl commentServiceImpl;
    
    @Autowired
    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllPost(){
        return commentServiceImpl.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional getPostById(@PathVariable Long id){
        return commentServiceImpl.findById(id);
    }
    
    @PostMapping
    public Comment createPost(@RequestBody Comment comment){
        return commentServiceImpl.save(comment);
    }
    
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        commentServiceImpl.deleteById(id);
    }
    
    
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        comment.setIdComment(id);
        return commentServiceImpl.save(comment);
    }
    
}
