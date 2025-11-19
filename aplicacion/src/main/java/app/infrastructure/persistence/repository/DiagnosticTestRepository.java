package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.DiagnosticTestEntity;
import java.util.List;

@Repository
public interface DiagnosticTestRepository extends JpaRepository<DiagnosticTestEntity, Long> {
    List<DiagnosticTestEntity> findByMedicalOrder_Id(Long medicalOrderId);
}
