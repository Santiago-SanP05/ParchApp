/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package campus.u2.parchap.like.application;

import campus.u2.parchap.like.domain.Like;
import java.util.List;

/**
 *
 * @author kevin
 */
public interface LikeService {
    List<Like> getAllTypeMaintenance();

    Like getTypeMaintenanceById(Long id);

    Like saveTypeMaintenance(Like like);

    void deleteTypeMaintenance(Long id);
}
