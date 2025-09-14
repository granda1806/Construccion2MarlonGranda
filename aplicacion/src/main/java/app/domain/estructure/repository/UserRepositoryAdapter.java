package app.domain.estructure.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import app.domain.User;
import app.domain.ports.UserPort;

@Repository
public class UserRepositoryAdapter implements UserPort {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public User findByDocument(User user) throws Exception {
        Optional<UserEntity> entity = userJpaRepository.findByDocument(user.getDocument());
        return entity.map(UserMapper::toDomain)
                     .orElseThrow(() -> new Exception("User not found with document: " + user.getDocument()));
    }
    
    public User findByName(User user) throws Exception {
        Optional<UserEntity> entity = userJpaRepository.findByName(user.getName());
        return entity.map(UserMapper::toDomain)
                     .orElseThrow(() -> new Exception("User not found with name: " + user.getName()));
    }

    public void save(User user) throws Exception {
        UserEntity entity = UserMapper.toEntity(user);
        userJpaRepository.save(entity);
    }

    @Override
    public app.domain.model.User findByDocument(app.domain.model.User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public app.domain.model.User findByName(app.domain.model.User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(app.domain.model.User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
