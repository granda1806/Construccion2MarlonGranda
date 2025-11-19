
package app.domain.ports;

import app.domain.model.Patient;
import app.domain.model.Policy;
import app.domain.model.enums.TypePolicy;
import java.util.List;

public interface PolicyPort {

    /**
     * Buscar póliza por ID
     */
    public Policy findById(Policy policy) throws Exception;

    /**
     * Guardar o crear nueva póliza
     */
    public void save(Policy policy) throws Exception;

    /**
     * Buscar todas las pólizas de un paciente
     */
    public List<Policy> findByPatient(Patient patient) throws Exception;

    /**
     * Buscar pólizas por tipo
     */
    public List<Policy> findByPolicyType(TypePolicy type) throws Exception;

    /**
     * Buscar póliza por número
     */
    public List<Policy> findByPolicyNumber(Long policyNumber) throws Exception;

    /**
     * Buscar todas las pólizas activas
     */
    public List<Policy> findAllActivePolicies() throws Exception;

    /**
     * Actualizar póliza existente
     */
    public void update(Policy policy) throws Exception;

    /**
     * Eliminar póliza por ID
     */
    public void delete(Long policyId) throws Exception;
}
