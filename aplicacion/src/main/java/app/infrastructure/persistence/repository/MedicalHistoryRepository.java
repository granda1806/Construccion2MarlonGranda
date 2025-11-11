
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistoryEntity, Integer> {

    List<MedicalHistoryEntity> findByPatientId(Long patientId);

    Optional<MedicalHistoryEntity> findByPatientIdAndDate(Long patientId, Date date);

    boolean existsByPatientId(Long patientId);
}
