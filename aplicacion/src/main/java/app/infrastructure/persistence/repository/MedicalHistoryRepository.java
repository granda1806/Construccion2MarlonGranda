<<<<<<< HEAD
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

=======
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio JPA para gestionar las historias clínicas de los pacientes.
 */
@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistoryEntity, Long> {

    /**
     * Busca todas las historias clínicas de un paciente
     * según su número de documento.
     *
     * @param patientDocument número de documento del paciente
     * @return lista de historias clínicas asociadas al documento
     */
    List<MedicalHistoryEntity> findByPatientDocument(Long patientDocument);

    public boolean existsByPatientId(Long patientId);
}
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
