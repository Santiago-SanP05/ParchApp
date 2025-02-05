/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.follower.domain;

import campus.u2.parchap.user.domain.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin
 */
public interface FollowerRepository {
    List<Follower> findAll();
    Follower save(Follower follower);
    Optional <Follower> findById(Long id);
    void deleteById(Long id);    
    void delete(Follower follower);
    List<Follower> findByUserFollower(User userFollower);
    List<Follower> findByUserFollowed(User userFollowed);
    Optional<Follower> findByUserFollowerAndUserFollowed(User userFollower, User userFollowed);
    Optional<Follower> findByUserFollower_IdUserAndUserFollowed_IdUser(Long followerId, Long followedId);
}
