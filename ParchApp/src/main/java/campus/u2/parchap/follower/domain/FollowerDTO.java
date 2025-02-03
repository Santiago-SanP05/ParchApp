
package campus.u2.parchap.follower.domain;

import java.time.LocalDateTime;

public class FollowerDTO {
    
    private Long idFollower;
    private LocalDateTime followDate;
    private Long userFollowerId;  // ID del usuario que sigue
    private Long userFollowedId;  // ID del usuario al que se sigue

    public FollowerDTO(Long idFollower, LocalDateTime followDate, Long userFollowerId, Long userFollowedId) {
        this.idFollower = idFollower;
        this.followDate = followDate;
        this.userFollowerId = userFollowerId;
        this.userFollowedId = userFollowedId;
    }

    public FollowerDTO(LocalDateTime followDate, Long userFollowerId, Long userFollowedId) {
        this.followDate = followDate;
        this.userFollowerId = userFollowerId;
        this.userFollowedId = userFollowedId;
    }

    public FollowerDTO() {
    }

    public Long getIdFollower() {
        return idFollower;
    }

    public void setIdFollower(Long idFollower) {
        this.idFollower = idFollower;
    }

    public LocalDateTime getFollowDate() {
        return followDate;
    }

    public void setFollowDate(LocalDateTime followDate) {
        this.followDate = followDate;
    }

    public Long getUserFollowerId() {
        return userFollowerId;
    }

    public void setUserFollowerId(Long userFollowerId) {
        this.userFollowerId = userFollowerId;
    }

    public Long getUserFollowedId() {
        return userFollowedId;
    }

    public void setUserFollowedId(Long userFollowedId) {
        this.userFollowedId = userFollowedId;
    }
    
    

}
