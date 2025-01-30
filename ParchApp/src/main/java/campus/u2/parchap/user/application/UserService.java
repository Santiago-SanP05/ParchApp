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
    List<User> getAllTypeMaintenance();

    User getTypeMaintenanceById(Long id);

    User saveTypeMaintenance(User user);

    void deleteTypeMaintenance(Long id);
}
