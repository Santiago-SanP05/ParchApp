/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.follower.domain;

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
}
