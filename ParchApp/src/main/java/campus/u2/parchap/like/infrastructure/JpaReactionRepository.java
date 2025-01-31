/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.like.infrastructure;

import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.like.domain.ReactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kevin
 */
public interface JpaReactionRepository extends JpaRepository<Reaction, Long>, ReactionRepository{
    
}
