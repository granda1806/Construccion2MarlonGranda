package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
<<<<<<< HEAD
    public UserEntity findById(long id);
=======
    UserEntity findByDocument(Long document);
    UserEntity findByUserName(String userName);
>>>>>>> 9542caac1b9325a98c02d414474bdf3298dc734a
}
