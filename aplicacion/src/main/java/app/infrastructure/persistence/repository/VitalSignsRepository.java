package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.infrastructure.persistence.entities.VitalSignsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSignsEntity, Long> {
    java.util.List<VitalSignsEntity> findByMedicalOrder_Id(Long medicalOrderId);
}
