package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import app.infrastructure.persistence.entities.UserEntity;

public interface UserRepositoryAdapter extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDocument(String document);
    Optional<UserEntity> findByUserName(String userName);
    boolean existsByDocument(String document);
}

