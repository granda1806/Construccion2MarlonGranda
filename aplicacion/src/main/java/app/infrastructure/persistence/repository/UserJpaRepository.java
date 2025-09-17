package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>
{
    
    Optional<UserEntity> findByDocument(Long document);
    Optional<UserEntity> findByName(String name);
    
}
