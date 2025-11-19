package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.MedicationEntity;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {
}
