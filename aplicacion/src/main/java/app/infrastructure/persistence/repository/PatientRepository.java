package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    // Use Optional and the boxed Long to avoid ambiguity and null-safety issues
    Optional<PatientEntity> findByDocument(Long document);

    // No necesitamos declarar findById, JpaRepository ya lo tiene

}
