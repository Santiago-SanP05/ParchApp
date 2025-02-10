package campus.u2.parchap;

import campus.u2.parchap.comment.infrastructure.CommentNotFoundException;
import campus.u2.parchap.follower.infrastructure.FollowersNotFoundException;
import campus.u2.parchap.like.infrastructure.ReactionNotFoundException;
import campus.u2.parchap.post.infrastructure.PostNotFoundException;
import campus.u2.parchap.user.infrastructure.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponses("User not found", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handleCommentNotFoundException(CommentNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponses("Comment not found", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handleReactionNotFoundException(ReactionNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponses("Reaction not found", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handlePostNotFoundException(PostNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponses("Post not found", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FollowersNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handleFollowersNotFoundException(FollowersNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponses("Followers not found", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponses> handleInvalidIdException(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorResponses("Invalid ID", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponses> handleValidationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ErrorResponses("Validation error", ex.getBindingResult().getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponses> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ErrorResponses("Data integrity violation", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponses> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponses("Application error", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
