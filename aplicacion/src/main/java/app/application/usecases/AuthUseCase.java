
package app.application.usecases;

import app.domain.model.User;
import app.domain.model.enums.Role;
import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.entities.AuthTokenEntity;
import app.infrastructure.persistence.repository.UserRepository;
import app.infrastructure.persistence.repository.AuthTokenRepository;
import app.infrastructure.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class AuthUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthTokenRepository tokenRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Inicia sesión, valida credenciales y genera un token híbrido (JWT + custom).
     */
    public String login(String userName, String password) throws Exception {
        UserEntity userEntity = userRepository.findByUserName(userName);

        if (userEntity == null) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }

        // Generar JWT
        String jwtPart = jwtService.generateToken(userEntity.getUserName(), userEntity.getRole().toString());

        // Token custom
        String customPart = generateCustomToken();

        // Mezclar aleatoriamente
        String combinedToken = mergeTokensRandomly(jwtPart, customPart);

        // Fechas de expiración
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + TimeUnit.MINUTES.toMillis(3)); // 3 min

        // Guardar token
        AuthTokenEntity tokenEntity = new AuthTokenEntity(userName, combinedToken, now, expiresAt);
        tokenRepository.save(tokenEntity);

        return String.format("""
                ✅ AUTENTICACIÓN EXITOSA
                Usuario: %s
                Rol: %s
                Token generado: %s
                Válido por: 3 minutos
                """, userEntity.getUserName(), userEntity.getRole(), combinedToken);
    }

    /**
     * Crea y guarda un nuevo usuario en la base de datos.
     */
    public void createUser(User user) throws Exception {
        if (user == null) {
            throw new Exception("El usuario no puede ser nulo.");
        }

        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new Exception("El nombre de usuario ya está en uso.");
        }

        UserEntity entity = new UserEntity();
        entity.setNameComplete(user.getNameComplete());
        entity.setLastnameComplete(user.getLastnameComplete());
        entity.setDocument(user.getDocument());
        entity.setAge(user.getAge());
        entity.setRole(user.getRole());
        entity.setUserName(user.getUserName());

        // Codificar contraseña
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new Exception("La contraseña no puede estar vacía.");
        }
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        // Guardar en BD
        userRepository.save(entity);
    }

    /**
     * Devuelve el rol del usuario autenticado.
     */
    public Role getUserRole(String userName) throws Exception {
        UserEntity userEntity = userRepository.findByUserName(userName);
        if (userEntity == null) {
            throw new Exception("Usuario no encontrado.");
        }
        return userEntity.getRole();
    }

    /**
     * Genera un token alfanumérico personalizado.
     */
    private String generateCustomToken() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder();

        for (int i = 1; i <= 30; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
            if (i % 5 == 0 && i != 30) token.append('.');
        }
        return token.toString();
    }

    /**
     * Mezcla aleatoriamente dos tokens.
     */
    private String mergeTokensRandomly(String jwt, String custom) {
        StringBuilder merged = new StringBuilder();
        SecureRandom random = new SecureRandom();

        int i = 0, j = 0;
        while (i < jwt.length() || j < custom.length()) {
            if (random.nextBoolean() && i < jwt.length()) {
                merged.append(jwt.charAt(i++));
            } else if (j < custom.length()) {
                merged.append(custom.charAt(j++));
            }
        }
        return merged.toString();
    }

    /**
     * Valida si el token sigue siendo válido.
     */
    public boolean validateToken(String token) {
        AuthTokenEntity tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity == null) return false;
        return new Date().before(tokenEntity.getExpiresAt());
    }

    /**
     * Elimina el token (logout).
     */
    public void logout(String token) {
        AuthTokenEntity tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity != null) tokenRepository.delete(tokenEntity);
    }

    /**
     * Busca un usuario por nombre de usuario.
     */
    public User findByUserName(String userName) {
        UserEntity entity = userRepository.findByUserName(userName);
        if (entity == null) return null;

        User user = new User();
        user.setId(entity.getId());
        user.setNameComplete(entity.getNameComplete());
        user.setLastnameComplete(entity.getLastnameComplete());
        user.setDocument(entity.getDocument());
        user.setAge(entity.getAge());
        user.setRole(entity.getRole());
        user.setUserName(entity.getUserName());
        user.setPassword(entity.getPassword());
        return user;
    }
}
