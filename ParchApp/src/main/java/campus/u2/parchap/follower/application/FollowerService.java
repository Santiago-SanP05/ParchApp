/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.follower.application;

import campus.u2.parchap.follower.domain.Follower;
import java.util.List;

/**
 *
 * @author kevin
 */
public interface FollowerService {
    List<Follower> getAllTypeMaintenance();

    Follower getTypeMaintenanceById(Long id);

    Follower saveTypeMaintenance(Follower follower);

    void deleteTypeMaintenance(Long id);
}
