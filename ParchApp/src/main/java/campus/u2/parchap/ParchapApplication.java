package campus.u2.parchap;

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
                
                User u1 = new User("Kevin", "Kevin123", "nvdsvnskd", "Kevin@gmail.com", "Kevin123", "Hola mundo", dateDeparture, dateArrived);
                userserviceimpl.save(u1);
                
                
	}

}
