package campus.u2.parchap;

import campus.u2.parchap.comment.application.CommentServiceImpl;
import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.follower.application.FollowerServiceImpl;
import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.like.application.ReactionServiceImpl;
import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.like.domain.ReactionDTO;
import campus.u2.parchap.post.application.PostServiceImpl;
import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.post.domain.PostDTO;
import campus.u2.parchap.user.application.UserServiceImpl;
import campus.u2.parchap.user.domain.User;
import campus.u2.parchap.user.domain.UserDTO;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ParchapApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(ParchapApplication.class, args);

        // Fechas de ejemplo
        LocalDateTime dateDeparture = LocalDateTime.of(2025, 3, 15, 14, 30);
        LocalDateTime dateArrived = LocalDateTime.of(2025, 3, 19, 14, 30);

        // Obtener los servicios de cada entidad
        UserServiceImpl userServiceImpl = contexto.getBean(UserServiceImpl.class);
        PostServiceImpl postServiceImpl = contexto.getBean(PostServiceImpl.class);
        CommentServiceImpl commentServiceImpl = contexto.getBean(CommentServiceImpl.class);
        ReactionServiceImpl reactionServiceImpl = contexto.getBean(ReactionServiceImpl.class);
        FollowerServiceImpl followerServiceImpl = contexto.getBean(FollowerServiceImpl.class);
        PasswordEncoder passwordEncoder = contexto.getBean(PasswordEncoder.class);


        // Crear un nuevo usuario (DTO)
        // Crear un objeto User con todos sus datos
        User user1 = new User();
        user1.setName("sassant");
        user1.setNameUser("Elsasant");
        user1.setUrlPhoto("http://example.com/sassant.jpg");
        user1.setEmail("nana@email.com");
        user1.setPassword("goodpoint");
        user1.setBiography("Soy un usuario nuevo en la aplicación.");

        User user2 = new User();
        user2.setName("Faring");
        user2.setNameUser("grrrrrr");
        user2.setUrlPhoto("http://example.com/sassant.jpg");
        user2.setEmail("ElDeariporo@email.com");
        user2.setPassword("Abeja");
        user2.setBiography("Soy un usuario nuevo en la aplicación.");

        User user3 = new User();
        user3.setName("exagon");
        user3.setNameUser("elnegrito");
        user3.setUrlPhoto("http://example.com/sassant.jpg");
        user3.setEmail("aña@email.com");
        user3.setPassword("barrancabermeja");
        user3.setBiography("Soy un usuario nuevo en la aplicación.");

        // Guardar los usuarios
        userServiceImpl.save(user1);
        userServiceImpl.save(user2);
        userServiceImpl.save(user3);

        // Crear un post para el usuario (DTO)
        Post p1 = new Post();
        p1.setCaption("Aquí tomando el sol");
        p1.setImageUrl("https://www.albitana.com/wp-content/uploads/2018/01/granja-escuela-albitana-1393.jpg");
        p1.setPublicationDate(LocalDateTime.now());
        p1.setUserPublication(user1);
//
//        // Guardar el post
        postServiceImpl.save(p1);
//
//        // Crear un comentario para el post (DTO)
        Comment comment = new Comment("¡Quiero estar en esas vaca-ciones!", dateArrived);
        comment.setCommentUser(user1); // Asignamos el usuario
        comment.setCommentPost(p1); // Asignamos el post
        commentServiceImpl.save(comment); // Guardar el comentario
//
//        // Crear la reacción para el post
        Reaction reaction = new Reaction(dateArrived);
        reaction.setLikePost(p1);  // Asociamos el post a la reacción
        reaction.setLikeUser(user1); // Asociamos el usuario que está reaccionando
        reactionServiceImpl.save(reaction); // Guardar la reacción
//
//        // Crear el seguidor (no es necesario usar DTO en este caso)
//        // Crear el seguidor (no es necesario usar DTO en este caso)
        Follower follower = new Follower();
////
////// Asignar el usuario seguidor (quien sigue)
//        follower.setUserFollower(user1); // Aquí user1 es quien sigue
////
////// Asignar el usuario seguido (quien es seguido)
//        follower.setUserFollowed(user2); // Aquí user2 es quien es seguido
////
////// Guardar el seguidor
//        followerServiceImpl.save(follower); // Guardar el seguidor
//
//// Relacionar los seguidores y seguidores seguidos
        user1.addFollower(follower); // Agregar al usuario como seguidor
        user2.addFollowed(follower); // Agregar al usuario como seguido
//
//// Guardar los cambios en los usuarios
        userServiceImpl.save(user1);
        userServiceImpl.save(user2);
        
//
        System.out.println("Usuarios, posts, comentarios, reacciones y relaciones guardadas exitosamente.");
        
    }
}
