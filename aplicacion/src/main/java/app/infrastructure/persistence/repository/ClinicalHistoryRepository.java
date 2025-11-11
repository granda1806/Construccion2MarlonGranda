
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio JPA para acceder a la tabla "medical_histories".
 * Permite realizar consultas relacionadas con las historias clínicas.
 */
@Repository
public interface ClinicalHistoryRepository extends JpaRepository<MedicalHistoryEntity, Long> {

    /**
     * Busca todas las historias clínicas asociadas al documento de un paciente.
     *
     * @param patientDocument Documento (cédula) del paciente.
     * @return Lista de historias clínicas encontradas.
     */
    List<MedicalHistoryEntity> findByPatientDocument(Long patientDocument);
}
