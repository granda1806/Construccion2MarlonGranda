import org.springframework.stereotype.Repository;
import java.util.Optional;
import app.domain.model.User;
import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.mapper.UserMapper;
import app.infrastructure.persistence.repository.UserJpaRepository;
import app.domain.ports.UserPortIn;
import app.domain.ports.UserPortOut;

@Repository
public class UserRepositoryAdapter implements UserPortOut
{

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository)
    {
        
        this.userJpaRepository = userJpaRepository;
        
    }

    @Override
    public User findByDocument(User user) throws Exception
    {
        
        Optional<UserEntity> entity = userJpaRepository.findByDocument(user.getDocument());
        
        return entity.map(UserMapper::toDomain)
                     .orElseThrow(() -> new Exception("User not found with document: " + user.getDocument()));
        
    }

    @Override
    public User findByName(User user) throws Exception
    {
        
        Optional<UserEntity> entity = userJpaRepository.findByName(user.getName());
        return entity.map(UserMapper::toDomain)
                     .orElseThrow(() -> new Exception("User not found with name: " + user.getUserName()));
        
    }

    @Override
    public void save(User user) throws Exception
    {
        
        UserEntity entity = UserMapper.toEntity(user);
        userJpaRepository.save(entity);
        
    }
    
}
