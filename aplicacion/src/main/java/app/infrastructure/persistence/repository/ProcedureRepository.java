package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.ProcedureEntity;
import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<ProcedureEntity, Long> {
    List<ProcedureEntity> findByMedicalOrder_Id(Long medicalOrderId);
}
