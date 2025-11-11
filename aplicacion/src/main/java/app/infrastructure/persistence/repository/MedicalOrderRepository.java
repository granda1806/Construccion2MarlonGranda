
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalOrderRepository extends JpaRepository<MedicalOrderEntity, Long> {

    MedicalOrderEntity findByOrderNumber(String orderNumber);

    void deleteByOrderNumber(String orderNumber);
}
