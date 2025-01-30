/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.user.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

public interface UserRepository {
    List<User> findAll();
    User save(User user);
    Optional<User> findById(Long id);
    void deleteById(Long id);
}
