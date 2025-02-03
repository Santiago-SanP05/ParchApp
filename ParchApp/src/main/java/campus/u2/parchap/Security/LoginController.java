/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.Security;

import campus.u2.parchap.user.domain.User;
import campus.u2.parchap.user.domain.UserDTO;
import campus.u2.parchap.user.domain.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserRepository userRepository;  // Asegúrate de tener un repositorio para acceder a los usuarios

    @Autowired
    private PasswordEncoder passwordEncoder;  // Utilizamos el PasswordEncoder para validar las contraseñas

    @PostMapping("login")
public ResponseEntity<UserDTO> login(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("email") String email) {
    try {
        Optional<User> userOptional = userRepository.findByName(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getEmail().equals(email)) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    String token = jwtAuthtenticationConfig.getJWTToken(user.getEmail());
                    return ResponseEntity.ok(new UserDTO(user.getName(), user.getEmail(), token));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new UserDTO("Invalid password", "", ""));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new UserDTO("Invalid email", "", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserDTO("User not found", "", ""));
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new UserDTO("Internal Server Error: " + e.getMessage(), "", ""));
    }
}
}