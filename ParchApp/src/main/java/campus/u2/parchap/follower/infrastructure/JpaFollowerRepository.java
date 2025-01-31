/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.follower.infrastructure;

import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.follower.domain.FollowerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kevin
 */
public interface JpaFollowerRepository extends JpaRepository<Follower, Long>, FollowerRepository{
    
}
