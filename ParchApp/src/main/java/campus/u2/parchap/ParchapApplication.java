package campus.u2.parchap;

import campus.u2.parchap.comment.application.CommentServiceImpl;
import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.comment.domain.CommentDTO;
import campus.u2.parchap.follower.application.FollowerServiceImpl;
import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.follower.domain.FollowerDTO;
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

        // Crear usuarios
        UserDTO u1 = new UserDTO();
        u1.setName("Juan Pérez");
        u1.setNameUser("juanp");
        u1.setEmail("juan.perez@example.com");
        u1.setBiography("Desarrollador de software");
        u1.setUrlPhoto("https://cdn-icons-png.flaticon.com/512/6326/6326055.png");
        u1.setPasword("noraaaa");

        UserDTO u2 = new UserDTO();
        u2.setName("enrique");
        u2.setNameUser("frrrrr");
        u2.setEmail("grrr@example.com");
        u2.setBiography("Desarrollador de software");
        u2.setUrlPhoto("https://cdn-icons-png.flaticon.com/512/6326/6326055.png");
        u2.setPasword("Alpha");

        UserDTO u3 = new UserDTO();
        u3.setName("NigaMan");
        u3.setNameUser("sassant");
        u3.setEmail("AliasMocoLargo@example.com");
        u3.setBiography("Desarrollador de software");
        u3.setUrlPhoto("https://cdn-icons-png.flaticon.com/512/6326/6326055.png");
        u3.setPasword("Alpha");

        // Guardar usuarios
        userServiceImpl.save(u1);
        userServiceImpl.save(u2);
        userServiceImpl.save(u3);

        // Crear posts
        PostDTO p1 = new PostDTO();
        p1.setImageUrl("https://pressover.news/wp-content/uploads/2021/11/5-57022_halo-3-hd-wallpaper-backgrounds-halo-2-scaled.jpg");
        p1.setCaption("Mi primer post");
        p1.setPublicationDate(LocalDateTime.now());
        p1.setUserId(1L); // Asignar al usuario con ID 1

        PostDTO p2 = new PostDTO();
        p2.setImageUrl("https://m.media-amazon.com/images/M/MV5BNjJjYWNlODMtNjQwZS00ZThiLThiMDktN2I4ODliYmNkNmVmXkEyXkFqcGdeQWFkcmllY2xh._V1_.jpg");
        p2.setCaption("Otro post interesante");
        p2.setPublicationDate(LocalDateTime.now());
        p2.setUserId(2L); // Asignar al usuario con ID 2
        
        // Guardar posts
        postServiceImpl.save(p1);
        postServiceImpl.save(p2);

        // Crear comentarios
        CommentDTO c1 = new CommentDTO();
        c1.setText("¡Gran post!");
        c1.setPublicationDate(LocalDateTime.now());
        c1.setIdUser(2L); // Comentario hecho por el usuario con ID 2
        c1.setIdPost(1L); // Comentario en el post con ID 1

        CommentDTO c2 = new CommentDTO();
        c2.setText("Me encanta esto.");
        c2.setPublicationDate(LocalDateTime.now());
        c2.setIdUser(3L); // Comentario hecho por el usuario con ID 3
        c2.setIdPost(1L); // Comentario en el post con ID 1

        // Guardar comentarios
        commentServiceImpl.save(c1);
        commentServiceImpl.save(c2);

        // Crear reacciones
        ReactionDTO r1 = new ReactionDTO();
        r1.setPublicationDate(LocalDateTime.now());
        r1.setIdUser(1L); // Reacción hecha por el usuario con ID 1
        r1.setIdPost(2L); // Reacción en el post con ID 2

        ReactionDTO r2 = new ReactionDTO();
        r2.setPublicationDate(LocalDateTime.now());
        r2.setIdUser(3L); // Reacción hecha por el usuario con ID 3
        r2.setIdPost(2L); // Reacción en el post con ID 2

// Guardar reacciones
        reactionServiceImpl.save(r1);
        reactionServiceImpl.save(r2);

        // Crear seguidores
        FollowerDTO f1 = new FollowerDTO();
        f1.setFollowDate(LocalDateTime.now());
        f1.setUserFollowerId(1L); // Usuario con ID 1 sigue a
        f1.setUserFollowedId(2L); // Usuario con ID 2

        FollowerDTO f2 = new FollowerDTO();
        f2.setFollowDate(LocalDateTime.now());
        f2.setUserFollowerId(2L); // Usuario con ID 2 sigue a
        f2.setUserFollowedId(3L); // Usuario con ID 3

        // Guardar seguidores
        followerServiceImpl.save(f1);
        followerServiceImpl.save(f2);
    }
}
