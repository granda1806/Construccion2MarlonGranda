
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    public AppointmentEntity findById(long id);
    
    public List<AppointmentEntity> findByPatient(PatientEntity entity);
}
