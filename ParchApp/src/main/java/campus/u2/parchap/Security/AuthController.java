/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.Security;

import campus.u2.parchap.user.domain.LoginRequest;
import campus.u2.parchap.user.domain.User;
import campus.u2.parchap.user.domain.UserDTO;
import campus.u2.parchap.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
public ResponseEntity<UserDTO> login(@RequestBody LoginRequest loginRequest) {
    // Validación de los datos recibidos
    if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new UserDTO("Username is required", "", ""));
    }

    if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new UserDTO("Email is required", "", ""));
    }

    if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new UserDTO("Password is required", "", ""));
    }

    try {
        // Busca al usuario en la base de datos por nombre de usuario
        Optional<User> userOptional = userRepository.findByNameUser(loginRequest.getUsername());

        // Verifica si el usuario existe
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserDTO("User not found", "", ""));
        }

        User user = userOptional.get();

        // Verificar que el correo coincida
        if (!user.getEmail().equals(loginRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserDTO("Invalid email", "", ""));
        }

        // Verifica si la contraseña es correcta (este es el bloque que mencionaste)
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserDTO("Invalid password", "", ""));
        }

        // Si todo está bien, genera el JWT
        String token = jwtAuthtenticationConfig.getJWTToken(user.getEmail());

        // Devuelve el DTO con los datos del usuario y el token generado
        return ResponseEntity.ok(new UserDTO(user.getName(), user.getEmail(), token));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new UserDTO("Internal Server Error: " + e.getMessage(), "", ""));
    }
}

}





