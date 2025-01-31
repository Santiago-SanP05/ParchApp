/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.like.domain;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin
 */
public interface ReactionRepository {
    List<Reaction> findAll();
    Reaction save(Reaction reaction);
    Optional <Reaction> findById(Long id);
    void deleteById(Long id);
}
