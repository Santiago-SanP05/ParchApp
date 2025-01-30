/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.user.application;

import campus.u2.parchap.user.domain.User;
import java.util.List;

/**
 *
 * @author kevin
 */
public interface UserService {
    List<User> getAllUser();

    User getUserById(Long id);

    User saveUser(User user);

    void deleteUser(Long id);
}
