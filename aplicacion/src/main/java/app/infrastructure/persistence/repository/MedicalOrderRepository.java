package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicalOrderRepository extends JpaRepository<MedicalOrderEntity, Long> {
    boolean existsByOrderNumber(String orderNumber);

    // Puede haber duplicados en BD; devolver lista para evitar error de resultado
    // no único
    List<MedicalOrderEntity> findByOrderNumber(String orderNumber);

    void deleteByOrderNumber(String orderNumber);

    @Query("SELECT m FROM MedicalOrderEntity m LEFT JOIN FETCH m.doctor WHERE m.patientDocument = ?1")
    List<MedicalOrderEntity> findByPatientDocument(Long patientDocument);
}
