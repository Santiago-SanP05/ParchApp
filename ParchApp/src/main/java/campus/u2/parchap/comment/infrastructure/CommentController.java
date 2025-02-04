package campus.u2.parchap.comment.infrastructure;

import campus.u2.parchap.comment.application.CommentServiceImpl;
import campus.u2.parchap.comment.domain.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    @Autowired
    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    // Obtener todos los comentarios
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDTO> getAllComments() {
        return commentServiceImpl.findAll();
    }

    // Obtener un comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        Optional<CommentDTO> comment = commentServiceImpl.findById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un comentario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
        return commentServiceImpl.save(commentDTO);
    }

    // Eliminar un comentario por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        commentServiceImpl.deleteById(id);
    }

    // Actualizar un comentario por ID
    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setIdComment(id);
        CommentDTO updatedComment = commentServiceImpl.save(commentDTO); // Usamos el DTO actualizado
        return ResponseEntity.ok(updatedComment);
    }
}
