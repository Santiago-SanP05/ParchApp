package campus.u2.parchap.comment.infrastructure;


public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String message) {
        super(message);
    }
}
