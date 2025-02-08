package campus.u2.parchap;

import campus.u2.parchap.comment.application.CommentServiceImpl;
import campus.u2.parchap.comment.domain.CommentDTO;
import campus.u2.parchap.follower.application.FollowerServiceImpl;
import campus.u2.parchap.follower.domain.FollowerDTO;
import campus.u2.parchap.like.application.ReactionServiceImpl;
import campus.u2.parchap.like.domain.ReactionDTO;
import campus.u2.parchap.post.application.PostServiceImpl;
import campus.u2.parchap.post.domain.PostDTO;
import campus.u2.parchap.user.application.UserServiceImpl;
import campus.u2.parchap.user.domain.UserDTO;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
        u1.setPassword("noraaaa");

        UserDTO u2 = new UserDTO();
        u2.setName("enrique");
        u2.setNameUser("frrrrr");
        u2.setEmail("grrr@example.com");
        u2.setBiography("Desarrollador de software");
        u2.setUrlPhoto("https://cdn-icons-png.flaticon.com/512/6326/6326055.png");
        u2.setPassword("Alpha");

        UserDTO u3 = new UserDTO();
        u3.setName("NigaMan");       
        u3.setNameUser("sassant");
        u3.setEmail("AliasMocoLargo@example.com");
        u3.setBiography("Desarrollador de software");
        u3.setUrlPhoto("https://cdn-icons-png.flaticon.com/512/6326/6326055.png");
        u3.setPassword("Alpha");
        
        UserDTO u4 = new UserDTO();
        u4.setName("NigaMan");
        u4.setNameUser("SS");
        u4.setEmail("AliasElena@example.com");
        u4.setBiography("Desarrollador de software");
        u4.setUrlPhoto("https://cdn-icons-png.flaticon.com/512/6326/6326055.png");
        u4.setPassword("Alpha");
        
        UserDTO u5 = new UserDTO();
        u5.setName("Santiago Santacruz");
        u5.setNameUser("sassant_Oof");
        u5.setEmail("santiago@gmail.com");
        u5.setBiography("Hola, mi nombre es santiago y me gusta la pepsi");
        u5.setUrlPhoto("https://cdn-icons-png.flaticon.com/512/6326/6326055.png");
        u5.setPassword("20052305");
        
        
        // Guardar usuarios
        userServiceImpl.save(u1);
        userServiceImpl.save(u2);
        userServiceImpl.save(u3);
        userServiceImpl.save(u4);
        userServiceImpl.save(u5);

        // Crear posts
        PostDTO p1 = new PostDTO();
        p1.setImageUrl("https://pressover.news/wp-content/uploads/2021/11/5-57022_halo-3-hd-wallpaper-backgrounds-halo-2-scaled.jpg");
        p1.setCaption("Mi primer post");
        p1.setPublicationDate(LocalDateTime.now());
        p1.setUserId(1L);

        PostDTO p2 = new PostDTO();
        p2.setImageUrl("https://m.media-amazon.com/images/M/MV5BNjJjYWNlODMtNjQwZS00ZThiLThiMDktN2I4ODliYmNkNmVmXkEyXkFqcGdeQWFkcmllY2xh._V1_.jpg");
        p2.setCaption("Otro post interesante");
        p2.setPublicationDate(LocalDateTime.now());
        p2.setUserId(2L); // Asignar al usuario con ID 2
        
        PostDTO p3 = new PostDTO();
        p3.setImageUrl("https://cdn.pixabay.com/photo/2022/05/08/18/30/broken-heart-7182718_1280.png");
        p3.setCaption("I'm depresed");
        p3.setPublicationDate(LocalDateTime.now());
        p3.setUserId(1L); // Asignar al usuario con ID 2
        
        PostDTO p4 = new PostDTO();
        p4.setImageUrl("https://wallpapers.com/images/featured/pantalla-4k-u3mo5lq8k93935zr.jpg");
        p4.setCaption("i this so ameising, or no?");
        p4.setPublicationDate(LocalDateTime.now());
        p4.setUserId(3L); // Asignar al usuario con ID 2
        
        PostDTO p5 = new PostDTO();
        p5.setImageUrl("https://img2.wallspic.com/previews/9/6/9/9/7/179969/179969-luz-diseno_grafico-arte_fractal-arte-diseno-500x.jpg");
        p5.setCaption("Brickell");
        p5.setPublicationDate(LocalDateTime.now());
        p5.setUserId(3L); // Asignar al usuario con ID 2
        
        PostDTO p6 = new PostDTO();
        p6.setImageUrl("https://wallpapershome.com/images/pages/ico_h/27175.jpg");
        p6.setCaption("I like coffe witn bread in the morning");
        p6.setPublicationDate(LocalDateTime.now());
        p6.setUserId(2L); // Asignar al usuario con ID 2
        
        PostDTO p7 = new PostDTO();
        p7.setImageUrl("https://png.pngtree.com/background/20231222/original/pngtree-modern-black-joysticks-set-on-abstract-light-wallpaper-perfect-for-gaming-picture-image_6936211.jpg");
        p7.setCaption("Una partida??");
        p7.setPublicationDate(LocalDateTime.now());
        p7.setUserId(5L);
        
        PostDTO p8 = new PostDTO();
        p8.setImageUrl("https://www.shutterstock.com/image-illustration/80s-retro-futuristic-scifi-background-260nw-1678324570.jpg");
        p8.setCaption("Mira, uuuuuun planeta, soy terraplanista");
        p8.setPublicationDate(LocalDateTime.now());
        p8.setUserId(5L);
        
        PostDTO p9 = new PostDTO();
        p9.setImageUrl("https://c4.wallpaperflare.com/wallpaper/575/318/511/assassin-s-creed-ii-video-games-assassin-s-creed-assassin-s-creed-brotherhood-wallpaper-preview.jpg");
        p9.setCaption("este es el mejor juego de mundo abierto");
        p9.setPublicationDate(LocalDateTime.now());
        p9.setUserId(5L);
        
        PostDTO p10 = new PostDTO();
        p10.setImageUrl("https://i.ytimg.com/vi/_JPaZvp-_rc/maxresdefault.jpg");
        p10.setCaption("I'm Volanding");
        p10.setPublicationDate(LocalDateTime.now());
        p10.setUserId(5L);
        // Guardar posts
        postServiceImpl.save(p1);
        postServiceImpl.save(p2);
        postServiceImpl.save(p3);
        postServiceImpl.save(p4);
        postServiceImpl.save(p5);
        postServiceImpl.save(p6);
        postServiceImpl.save(p7);
        postServiceImpl.save(p8);
        postServiceImpl.save(p9);
        postServiceImpl.save(p10);

        // Crear comentarios
        CommentDTO c1 = new CommentDTO();
        c1.setText("¡Gran post!");
        c1.setPublicationDate(LocalDateTime.now());
        c1.setIdUser(1L); // Comentario hecho por el usuario con ID 2
        c1.setIdPost(7L); // Comentario en el post con ID 1

        CommentDTO c2 = new CommentDTO();
        c2.setText("Amigo busca vida social");
        c2.setPublicationDate(LocalDateTime.now());
        c2.setIdUser(2L); // Comentario hecho por el usuario con ID 3
        c2.setIdPost(8L); // Comentario en el post con ID 1
        
        CommentDTO c3 = new CommentDTO();
        c3.setText("Whaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaat?????");
        c3.setPublicationDate(LocalDateTime.now());
        c3.setIdUser(3L); // Comentario hecho por el usuario con ID 3
        c3.setIdPost(2L); // Comentario en el post con ID 1
        
        CommentDTO c4 = new CommentDTO();
        c4.setText("Para entender la historia de Five Nights at Freddy's, necesitas entender la historia de Five Nights at Freddy's. ");
        c4.setPublicationDate(LocalDateTime.now());
        c4.setIdUser(1L); // Comentario hecho por el usuario con ID 3
        c4.setIdPost(8L); // Comentario en el post con ID 1
        
        CommentDTO c5 = new CommentDTO();
        c5.setText("I im super happy wow");
        c5.setPublicationDate(LocalDateTime.now());
        c5.setIdUser(2L); // Comentario hecho por el usuario con ID 3
        c5.setIdPost(1L); // Comentario en el post con ID 1
        
        CommentDTO c6 = new CommentDTO();
        c6.setText("ufffff");
        c6.setPublicationDate(LocalDateTime.now());
        c6.setIdUser(3L); // Comentario hecho por el usuario con ID 3
        c6.setIdPost(1L); // Comentario en el post con ID 1
        
        CommentDTO c7 = new CommentDTO();
        c7.setText("Creer es saber");
        c7.setPublicationDate(LocalDateTime.now());
        c7.setIdUser(1L); // Comentario hecho por el usuario con ID 3
        c7.setIdPost(5L); // Comentario en el post con ID 1
        
        CommentDTO c8 = new CommentDTO();
        c8.setText("Cuando el rio suena, se lo lleva la corriente");
        c8.setPublicationDate(LocalDateTime.now());
        c8.setIdUser(2L); // Comentario hecho por el usuario con ID 3
        c8.setIdPost(5L); // Comentario en el post con ID 1
        
        CommentDTO c9 = new CommentDTO();
        c9.setText("Y recuerda, recordar");
        c9.setPublicationDate(LocalDateTime.now());
        c9.setIdUser(3L); // Comentario hecho por el usuario con ID 3
        c9.setIdPost(5L); // Comentario en el post con ID 1
        
        CommentDTO c10 = new CommentDTO();
        c10.setText("Y la queso");
        c10.setPublicationDate(LocalDateTime.now());
        c10.setIdUser(1L); // Comentario hecho por el usuario con ID 3
        c10.setIdPost(6L); // Comentario en el post con ID 1

        // Guardar comentarios
        commentServiceImpl.save(c1);
        commentServiceImpl.save(c2);
        commentServiceImpl.save(c3);
        commentServiceImpl.save(c4);
        commentServiceImpl.save(c5);
        commentServiceImpl.save(c6);
        commentServiceImpl.save(c7);
        commentServiceImpl.save(c8);
        commentServiceImpl.save(c9);
        commentServiceImpl.save(c10);

        // Crear reacciones
        ReactionDTO r1 = new ReactionDTO();
        r1.setPublicationDate(LocalDateTime.now());
        r1.setIdUser(1L); // Reacción hecha por el usuario con ID 1
        r1.setIdPost(10L); // Reacción en el post con ID 2

        ReactionDTO r2 = new ReactionDTO();
        r2.setPublicationDate(LocalDateTime.now());
        r2.setIdUser(3L); // Reacción hecha por el usuario con ID 3
        r2.setIdPost(2L); // Reacción en el post con ID 2
        
        ReactionDTO r3 = new ReactionDTO();
        r3.setPublicationDate(LocalDateTime.now());
        r3.setIdUser(1L); // Reacción hecha por el usuario con ID 3
        r3.setIdPost(8L); // Reacción en el post con ID 2
        
        ReactionDTO r4 = new ReactionDTO();
        r4.setPublicationDate(LocalDateTime.now());
        r4.setIdUser(1L); // Reacción hecha por el usuario con ID 3
        r4.setIdPost(3L); // Reacción en el post con ID 2
        
        ReactionDTO r5 = new ReactionDTO();
        r5.setPublicationDate(LocalDateTime.now());
        r5.setIdUser(1L); // Reacción hecha por el usuario con ID 3
        r5.setIdPost(4L); // Reacción en el post con ID 2
        
        ReactionDTO r6 = new ReactionDTO();
        r6.setPublicationDate(LocalDateTime.now());
        r6.setIdUser(1L); // Reacción hecha por el usuario con ID 3
        r6.setIdPost(5L); // Reacción en el post con ID 2
        
        ReactionDTO r7 = new ReactionDTO();
        r7.setPublicationDate(LocalDateTime.now());
        r7.setIdUser(2L); // Reacción hecha por el usuario con ID 3
        r7.setIdPost(6L); // Reacción en el post con ID 2
        
        ReactionDTO r8 = new ReactionDTO();
        r8.setPublicationDate(LocalDateTime.now());
        r8.setIdUser(3L); // Reacción hecha por el usuario con ID 3
        r8.setIdPost(8L); // Reacción en el post con ID 2


        // Guardar reacciones
        reactionServiceImpl.save(r1);
        reactionServiceImpl.save(r2);
        reactionServiceImpl.save(r3);
        reactionServiceImpl.save(r4);
        reactionServiceImpl.save(r5);
        reactionServiceImpl.save(r6);
        reactionServiceImpl.save(r7);
        reactionServiceImpl.save(r8);

        // Crear seguidores
        FollowerDTO f1 = new FollowerDTO();
        f1.setFollowDate(LocalDateTime.now());
        f1.setUserFollowerId(1L); // Usuario con ID 1 sigue a
        f1.setUserFollowedId(2L); // Usuario con ID 2

        FollowerDTO f2 = new FollowerDTO();
        f2.setFollowDate(LocalDateTime.now());
        f2.setUserFollowerId(2L); // Usuario con ID 2 sigue a
        f2.setUserFollowedId(3L); // Usuario con ID 3
        
        FollowerDTO f3 = new FollowerDTO();
        f3.setFollowDate(LocalDateTime.now());
        f3.setUserFollowerId(1L); // Usuario con ID 2 sigue a
        f3.setUserFollowedId(3L); // Usuario con ID 3
        
        FollowerDTO f4 = new FollowerDTO();
        f4.setFollowDate(LocalDateTime.now());
        f4.setUserFollowerId(1L); // Usuario con ID 2 sigue a
        f4.setUserFollowedId(5L); // Usuario con ID 3
        
        FollowerDTO f5 = new FollowerDTO();
        f5.setFollowDate(LocalDateTime.now());
        f5.setUserFollowerId(2L); // Usuario con ID 2 sigue a
        f5.setUserFollowedId(5L); // Usuario con ID 3
        
        
        FollowerDTO f6 = new FollowerDTO();
        f6.setFollowDate(LocalDateTime.now());
        f6.setUserFollowerId(3L); // Usuario con ID 2 sigue a
        f6.setUserFollowedId(5L); // Usuario con ID 3
        
        FollowerDTO f7 = new FollowerDTO();
        f7.setFollowDate(LocalDateTime.now());
        f7.setUserFollowerId(4L); // Usuario con ID 2 sigue a
        f7.setUserFollowedId(5L); // Usuario con ID 3

        // Guardar seguidores
        followerServiceImpl.save(f1);
        followerServiceImpl.save(f2);
        followerServiceImpl.save(f3);
        followerServiceImpl.save(f4);
        followerServiceImpl.save(f5);
        followerServiceImpl.save(f6);
        followerServiceImpl.save(f7);

    }
}
