package app.infrastructure.persistence.repository;
        
import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepositoryAdapter extends JpaRepository<UserEntity, Long> {
    
    public UserEntity findByDocument(long document);
    
    public UserEntity findByUserName(String userName);
}
