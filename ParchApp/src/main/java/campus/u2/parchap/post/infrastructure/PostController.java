/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.post.infrastructure;

import campus.u2.parchap.post.application.PostServiceImpl;
import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://127.0.0.1:5500") 
@RestController
@RequestMapping("api/post")
public class PostController {

    
    
    private final PostServiceImpl postServiceImpl;
    
    @Autowired
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPost(){
        return postServiceImpl.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional getPostById(@PathVariable Long id){
        return postServiceImpl.findById(id);
    }
    
    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postServiceImpl.save(post);
    }
    
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postServiceImpl.deleteById(id);
    }
    
    
    
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post){
        post.setIdPost(id);
        return postServiceImpl.save(post);
    }
    
}
