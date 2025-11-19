package app.application.usecases;

import app.domain.model.Policy;
import app.domain.model.enums.TypePolicy;
import app.domain.ports.PolicyPort;
import app.domain.services.UpdatePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePolicyUseCase {

    @Autowired
    private UpdatePolicy updatePolicy;

    @Autowired
    private PolicyPort policyPort;

    /**
     * Actualizar el estado de una póliza (activar/desactivar)
     */
    public Policy updatePolicyStatus(Long policyId, boolean newStatus) throws Exception {
        Policy policy = new Policy();
        policy.setId(policyId);
        Policy existing = policyPort.findById(policy);

        if (existing == null) {
            throw new Exception("Póliza no encontrada con id: " + policyId);
        }

        existing.setPolicyStatus(newStatus);
        updatePolicy.update(existing);

        return existing;
    }

    /**
     * Actualizar la fecha de terminación de la póliza
     */
    public Policy updatePolicyTerminationDate(Long policyId, java.sql.Date terminationDate) throws Exception {
        Policy policy = new Policy();
        policy.setId(policyId);
        Policy existing = policyPort.findById(policy);

        if (existing == null) {
            throw new Exception("Póliza no encontrada con id: " + policyId);
        }

        if (terminationDate == null) {
            throw new Exception("Fecha de terminación no puede ser nula");
        }

        existing.setPolicyTerminationDate(terminationDate);
        updatePolicy.update(existing);

        return existing;
    }

    /**
     * Actualizar número de póliza
     */
    public Policy updatePolicyNumber(Long policyId, Long newPolicyNumber) throws Exception {
        Policy policy = new Policy();
        policy.setId(policyId);
        Policy existing = policyPort.findById(policy);

        if (existing == null) {
            throw new Exception("Póliza no encontrada con id: " + policyId);
        }

        if (newPolicyNumber == null || newPolicyNumber <= 0) {
            throw new Exception("Número de póliza inválido");
        }

        existing.setPolicyNumber(newPolicyNumber);
        updatePolicy.update(existing);

        return existing;
    }

    /**
     * Actualizar el tipo de póliza
     */
    public Policy updatePolicyType(Long policyId, TypePolicy newType) throws Exception {
        Policy policy = new Policy();
        policy.setId(policyId);
        Policy existing = policyPort.findById(policy);

        if (existing == null) {
            throw new Exception("Póliza no encontrada con id: " + policyId);
        }

        if (newType == null) {
            throw new Exception("Tipo de póliza no puede ser nulo");
        }

        existing.setPolicyName(newType);
        updatePolicy.update(existing);

        return existing;
    }

    /**
     * Actualizar todos los campos de la póliza
     */
    public Policy updatePolicy(Policy updatedPolicy) throws Exception {
        if (updatedPolicy.getId() == null) {
            throw new Exception("ID de póliza requerido para actualizar");
        }

        Policy existing = policyPort.findById(updatedPolicy);
        if (existing == null) {
            throw new Exception("Póliza no encontrada con id: " + updatedPolicy.getId());
        }

        // Actualizar solo campos no nulos
        if (updatedPolicy.getPolicyNumber() != null && updatedPolicy.getPolicyNumber() > 0) {
            existing.setPolicyNumber(updatedPolicy.getPolicyNumber());
        }
        if (updatedPolicy.getPolicyName() != null) {
            existing.setPolicyName(updatedPolicy.getPolicyName());
        }
        if (updatedPolicy.getPolicyTerminationDate() != null) {
            existing.setPolicyTerminationDate(updatedPolicy.getPolicyTerminationDate());
        }
        existing.setPolicyStatus(updatedPolicy.isPolicyStatus());

        updatePolicy.update(existing);

        return existing;
    }
}
