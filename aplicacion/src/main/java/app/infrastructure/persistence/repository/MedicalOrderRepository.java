package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalOrderRepository extends JpaRepository<MedicalOrderEntity, Long> {
    public MedicalOrderEntity findByOrderNumber(String orderNumber);
    

}
