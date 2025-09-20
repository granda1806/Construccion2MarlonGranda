package app.adapter.out;

import app.domain.model.User;
import app.domain.ports.UserPortOut;
import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.infrastructure.persistence.repository.UserRepository;

@Service
public class UserAdapter implements UserPortOut {
    @Autowired
    private UserRepository userRepository;
    

    @Override
    public User findByDocument(User user) throws Exception {
        UserEntity userEntity = userRepository.findById(user.getId());
        return UserMapper.toDomain(userEntity);
 
    }

    @Override
    public User findByName(User user) throws Exception
    {
        return null;   
    }

    @Override
    public void save(User user) throws Exception {
        userRepository.save(UserMapper.toEntity(user));
        System.out.println("Se ha creado el usuario.");  
    }
    
}
