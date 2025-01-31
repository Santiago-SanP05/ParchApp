/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.follower.application;

import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.follower.domain.FollowerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kevin
 */
@Service
public class FollowerServiceImpl implements FollowerRepository{
    
    private final FollowerRepository followerRepository;
    
    @Autowired
    public FollowerServiceImpl(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }
    
    @Override
    public List<Follower> findAll() {
        return followerRepository.findAll();
    }

    @Override
    public Follower save(Follower follower) {
        return followerRepository.save(follower);
    }

    @Override
    public Optional<Follower> findById(Long id) {
        return followerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        followerRepository.deleteById(id);
    }
    
}
