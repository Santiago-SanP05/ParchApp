/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.user.infrastructure;

import campus.u2.parchap.user.application.UserServiceImpl;
import campus.u2.parchap.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kevin
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    
    private final UserServiceImpl userServiceImpl;
    
    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser(){
        return userServiceImpl.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional getUserById(@PathVariable Long id){
        return userServiceImpl.findById(id);
    }
    
    @PostMapping
    public User createUser(@RequestBody User user){
        return userServiceImpl.save(user);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userServiceImpl.deleteById(id);
    }

}
