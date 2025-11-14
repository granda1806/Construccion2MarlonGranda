package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para gestionar las historias clínicas de los pacientes.
 */
@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistoryEntity, Long> {

    /**
     * Busca todas las historias clínicas de un paciente por su ID.
     *
     * @param patientId ID del paciente
     * @return lista de historias clínicas asociadas al paciente
     */
    List<MedicalHistoryEntity> findByPatientId(Long patientId);

    /**
     * Busca una historia clínica específica por paciente y fecha.
     *
     * @param patientId ID del paciente
     * @param date fecha de la historia clínica
     * @return Optional con la historia clínica si existe
     */
    Optional<MedicalHistoryEntity> findByPatientIdAndDate(Long patientId, Date date);

    /**
     * Busca todas las historias clínicas de un paciente según su número de documento.
     *
     * @param patientDocument número de documento del paciente
     * @return lista de historias clínicas asociadas al documento
     */
    List<MedicalHistoryEntity> findByPatientDocument(Long patientDocument);

    /**
     * Verifica si existe una historia clínica para un paciente.
     *
     * @param patientId ID del paciente
     * @return true si existe al menos una historia clínica para el paciente
     */
    boolean existsByPatientId(Long patientId);
}