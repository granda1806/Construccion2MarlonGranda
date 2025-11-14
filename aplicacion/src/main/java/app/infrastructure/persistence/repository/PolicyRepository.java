
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.PolicyEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<PolicyEntity, Long>{
    public PolicyEntity findById(long id);
    
    public List<PolicyEntity> findByPatient(PatientEntity entity);
    
}
