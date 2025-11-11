
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.AuthTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthTokenEntity, Long> {
    AuthTokenEntity findByUserName(String userName);
    AuthTokenEntity findByToken(String token);
}
