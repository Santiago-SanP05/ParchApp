/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.like.application;

import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.like.domain.ReactionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kevin
 */
@Service
public class ReactionServiceImpl implements ReactionRepository{
    private final ReactionRepository reactionRepository;
    
    @Autowired
    public ReactionServiceImpl(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    @Override
    public List<Reaction> findAll() {
        return reactionRepository.findAll();
    }

    @Override
    public Reaction save(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    @Override
    public Optional<Reaction> findById(Long id) {
        return reactionRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        reactionRepository.deleteById(id);
    }
}
