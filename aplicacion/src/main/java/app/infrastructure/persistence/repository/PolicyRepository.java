
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.PolicyEntity;
import app.domain.model.enums.TypePolicy;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {

    /**
     * Buscar póliza por ID
     */
    @EntityGraph(attributePaths = { "admin", "patient" })
    public PolicyEntity findById(long id);

    /**
     * Buscar todas las pólizas de un paciente específico
     */
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findByPatient(PatientEntity entity);

    /**
     * Buscar pólizas por paciente ID
     */
    @Query("SELECT p FROM PolicyEntity p WHERE p.patient.id = :patientId")
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findByPatientId(@Param("patientId") Long patientId);

    /**
     * Buscar pólizas por tipo
     */
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findByNamePolicy(TypePolicy type);

    /**
     * Buscar póliza por número
     */
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findByPolicyNumber(Long policyNumber);

    /**
     * Buscar todas las pólizas activas
     */
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findByPolicyStatusTrue();

    /**
     * Buscar todas las pólizas inactivas
     */
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findByPolicyStatusFalse();

    /**
     * Buscar pólizas activas de un paciente específico
     */
    @Query("SELECT p FROM PolicyEntity p WHERE p.patient.id = :patientId AND p.policyStatus = true")
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findActivePoliciesByPatientId(@Param("patientId") Long patientId);

    /**
     * Buscar pólizas inactivas de un paciente específico
     */
    @Query("SELECT p FROM PolicyEntity p WHERE p.patient.id = :patientId AND p.policyStatus = false")
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findInactivePoliciesByPatientId(@Param("patientId") Long patientId);

    /**
     * Buscar pólizas por paciente y tipo
     */
    @Query("SELECT p FROM PolicyEntity p WHERE p.patient.id = :patientId AND p.namePolicy = :type")
    @EntityGraph(attributePaths = { "admin", "patient" })
    public List<PolicyEntity> findByPatientIdAndType(@Param("patientId") Long patientId,
            @Param("type") TypePolicy type);
}
