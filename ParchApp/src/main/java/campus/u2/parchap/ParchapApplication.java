package campus.u2.parchap;

import campus.u2.parchap.comment.application.CommentServiceImpl;
import campus.u2.parchap.comment.domain.Comment;
import campus.u2.parchap.follower.application.FollowerServiceImpl;
import campus.u2.parchap.follower.domain.Follower;
import campus.u2.parchap.follower.infrastructure.FollowerController;
import campus.u2.parchap.like.application.ReactionServiceImpl;
import campus.u2.parchap.like.domain.Reaction;
import campus.u2.parchap.post.application.PostServiceImpl;
import campus.u2.parchap.post.domain.Post;
import campus.u2.parchap.user.application.UserServiceImpl;
import campus.u2.parchap.user.domain.User;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ParchapApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(ParchapApplication.class, args);
                
                LocalDateTime dateDeparture = LocalDateTime.of(2025, 3, 15, 14, 30);
                LocalDateTime dateArrived = LocalDateTime.of(2025, 3, 19, 14, 30);
                
                UserServiceImpl userserviceimpl = contexto.getBean(UserServiceImpl.class);
                
                User u1 = new User("Kevinrr", "Kevin1231527", "587578", "Kevin@gmail.com", "Kevin1235725", "Hola mundo", dateDeparture, dateArrived);
                userserviceimpl.save(u1);
                
                
                PostServiceImpl postserviceimpl = contexto.getBean(PostServiceImpl.class);
                Post p1 = new Post("aqui de vaca-ciones", "urllavaquita", dateArrived);
                
                postserviceimpl.save(p1);
                
                CommentServiceImpl commentServiceImpl = contexto.getBean(CommentServiceImpl.class);
                
                Comment comment = new Comment("quiero estar en esas vaca-ciones", dateArrived);
                commentServiceImpl.save(comment);
                
                ReactionServiceImpl reactionServiceImpl = contexto.getBean(ReactionServiceImpl.class);
                
                Reaction reaction = new Reaction(dateArrived);
                reactionServiceImpl.save(reaction);
                
                FollowerServiceImpl followerServiceImpl = contexto.getBean(FollowerServiceImpl.class);
                
                Follower follower = new Follower(dateArrived);
                followerServiceImpl.save(follower);
                
	}

}
