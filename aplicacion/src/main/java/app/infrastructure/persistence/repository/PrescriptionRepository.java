package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.PrescriptionEntity;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {
    List<PrescriptionEntity> findByMedicalOrder_Id(Long medicalOrderId);
}
