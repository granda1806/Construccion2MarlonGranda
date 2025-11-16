package app.application.usecases;

import java.util.Random;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.domain.model.User;
import app.infrastructure.persistence.entities.AuthTokenEntity;
import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.mapper.UserMapper;
import app.infrastructure.persistence.repository.AuthTokenRepository;
import app.infrastructure.security.JwtService;
import app.infrastructure.persistence.repository.UserRepository;
import java.util.Date;

@Service
public class AuthUseCase
{
    
    /* Declarations for Token*/
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS_x = "0123456789";
    private static final String SPECIAL = "¡!¿?~,.;:-_";
    private static final int TOTAL_LENGTH = 30;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthTokenRepository authTokenRepository;

    public void createUser(User user) throws Exception
    {
        if (user == null)
        {
            
            throw new Exception("El usuario no puede ser nulo.");
            
        }

        if (userRepository.findByUserName(user.getUserName()) != null)
        {
            
            throw new Exception("El nombre de usuario ya está en uso.");
            
        }

        // Asignar la contraseña codificada al modelo
        if (user.getPassword() == null || user.getPassword().isEmpty())
        {
            
            throw new Exception("La contraseña no puede estar vacía.");
            
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Convertir el modelo a entidad usando el mapper
        UserEntity entity = UserMapper.toEntity(user);

        // Guardar en BD
        userRepository.save(entity);
        
    }

    
    public String generateJwtForUser(User user)
    {
        
        return jwtService.generateToken(user.getUserName(), user.getRole().toString());
        
    }
    
    /*
        Strategic generation of authentication tokens
    */
    public String customToken()
    {
        
        StringBuilder token = new StringBuilder();
        
        Random random = new Random();
        
        int specialCount = 0;
        char lastChar = 0;
        
        while (token.length() < TOTAL_LENGTH)
        {
            
            int type = random.nextInt(3); // 0=letter, 1=number, 2=special

            char nextChar;

            if (type == 2 && specialCount >= 5)
            {
                
                type = 1;
                
            }
            
            switch (type)
            {
                case 0 -> nextChar = LETTERS.charAt(random.nextInt(LETTERS.length()));
                
                case 1 -> nextChar = NUMBERS_x.charAt(random.nextInt(NUMBERS_x.length()));
                
                case 2 ->
                {
                    
                    char specialChar;
                    
                    do
                    {
                        
                        specialChar = SPECIAL.charAt(random.nextInt(SPECIAL.length()));
                        
                    }
                    while (lastChar == specialChar); // Avoid consecutive repetition

                    nextChar = specialChar;
                    
                    specialCount++;
                    
                }

                default -> throw new IllegalStateException("Unexpected value: " + type);
                
            }
            
            token.append(nextChar);
            
            lastChar = nextChar;
            
        }
        
        return token.substring(0, 5) + "." +
               token.substring(5, 10) + "." +
               token.substring(10, 15) + "." +
               token.substring(15, 20) + "." +
               token.substring(20, 25) + "." +
               token.substring(25, 30);
        
    }

    /*
        Segmented generation of passwords for assigned users
    */
    public void assignPasswordToUser(User user)
    {
    
        String password = generatePassword(10);
        user.setPassword(password);
        
    }
    
    private String generatePassword(int length)
    {
        
        if (length < 4)
        {
            
            throw new IllegalArgumentException("length must be >= 4");
            
        }

        final String U = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String L = "abcdefghijklmnopqrstuvwxyz";
        final String D = "0123456789";
        final String S = "+-*/.,_";
        final String ALL = U + L + D + S;

        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        sb.append(U.charAt(rnd.nextInt(U.length())));
        sb.append(L.charAt(rnd.nextInt(L.length())));
        sb.append(D.charAt(rnd.nextInt(D.length())));
        sb.append(S.charAt(rnd.nextInt(S.length())));

        for (int i = 4; i < length; i++)
        {
            
            sb.append(ALL.charAt(rnd.nextInt(ALL.length())));
            
        }

        // Mezcla aleatoria final
        char[] arr = sb.toString().toCharArray();
        for (int i = arr.length - 1; i > 0; i--)
        {
            
            int j = rnd.nextInt(i + 1);
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            
        }

        return new String(arr);
        
    }
    
    public boolean userExists(String userName)
    {
        
        return userRepository.findByUserName(userName) != null;
        
    }
    
    public User findByUserName(String userName)
    {
        
        UserEntity entity = userRepository.findByUserName(userName);
        if (entity == null) return null;
        
        return UserMapper.toDomain(entity);
        
    }
    
    public boolean passwordIsCorrect(String userName, String password)
    {
        
        UserEntity user = userRepository.findByUserName(userName);

        if (user == null) return false;
        
        return userRepository.findByUserName(userName) != null;
        
    }
    
    public boolean userHasActiveToken(String userName)
    {
        AuthTokenEntity token = authTokenRepository.findByUserName(userName);

        if (token == null)
        {
            
            return false;
            
        }

        Date expiresAt = token.getExpiresAt();

        if (expiresAt == null)
        {
            
            return false;
            
        }

        long now = System.currentTimeMillis();

        return expiresAt.getTime() > now;
        
    }
    
    public boolean tokenIsValid(String userName, String inputToken)
    {
        
        AuthTokenEntity token = authTokenRepository.findByUserName(userName);

        if (token == null) return false;

        long now = System.currentTimeMillis();
        
        if (token.getExpiresAt().getTime() < now)
        {
            
            authTokenRepository.delete(token);
            
            return false;
            
        }
        
        boolean matches = token.getToken().equals(inputToken);

        if (matches)
        {
            
            authTokenRepository.delete(token);
            
        }

        return matches;
        
    }
    
    public String generateTokenForUser(String userName)
    {

        long now = System.currentTimeMillis();
        
        if (userHasActiveToken(userName))
        {
            
            return null;
            
        }
        
        String token = customToken();

        AuthTokenEntity entity = new AuthTokenEntity();
        
        entity.setUserName(userName);
        
        entity.setToken(token);
        
        entity.setCreatedAt(new Date(now));
        
        entity.setExpiresAt(new Date(now + 60 * 60 * 1000));

        authTokenRepository.save(entity);

        return token;
        
    }
    
    public boolean consumeToken(String userName)
    {
        
        try
        {
            
            return authTokenRepository.deleteByUserName(userName);
            
        }
        catch (Exception e)
        {
            
            System.out.println("Error al eliminar el token: " + e.getMessage());
            
            return false;
            
        }
        
    }

    public boolean validateAdmnin (String userName, String password)
    {
        
        return "Admin".equals(userName) && "','".equals(password);
    }
    
}