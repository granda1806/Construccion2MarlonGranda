package app.domain.estructure.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDocument(String document);
    Optional<UserEntity> findByName(String name);
}
