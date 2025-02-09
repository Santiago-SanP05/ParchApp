package campus.u2.parchap.follower.infrastructure;

public class FollowersNotFoundException extends RuntimeException{
    public FollowersNotFoundException(String message) {
        super(message);
    }
}
