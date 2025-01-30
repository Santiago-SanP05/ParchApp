/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.follower.domain;

import campus.u2.parchap.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author kevin
 */
@Entity
public class Follower {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long idFollower;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followerId")
    private User userFollower;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followedId")
    private User userFollowed;
    
   
    private LocalDateTime followDate;

    public Follower() {
    }
    
    public Follower(User userFollower, User userFollowed, LocalDateTime followDate) {
    this.userFollower = userFollower;
    this.userFollowed = userFollowed;
    this.followDate = followDate;
}

    public Follower(LocalDateTime followDate) {
        this.followDate = followDate;
    }

    public Long getIdFollower() {
        return idFollower;
    }

    public void setIdFollower(Long idFollower) {
        this.idFollower = idFollower;
    }

    public User getUserFollower() {
        return userFollower;
    }

    public void setUserFollower(User userFollower) {
        this.userFollower = userFollower;
    }

    public User getUserFollowed() {
        return userFollowed;
    }

    public void setUserFollowed(User userFollowed) {
        this.userFollowed = userFollowed;
    }

    public LocalDateTime getFollowDate() {
        return followDate;
    }

    public void setFollowDate(LocalDateTime followDate) {
        this.followDate = followDate;
    }
    
    
    
}
