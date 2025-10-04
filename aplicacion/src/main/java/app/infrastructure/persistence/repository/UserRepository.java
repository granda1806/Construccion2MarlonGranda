package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByDocument(Long document);
    UserEntity findByUserName(String userName);
}
