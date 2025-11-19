package app.domain.services;

import app.domain.model.Policy;
import app.domain.ports.PolicyPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePolicy {

    @Autowired
    private PolicyPort policyPort;

    /**
     * Actualizar una póliza existente
     */
    public void update(Policy policy) throws Exception {
        if (policy == null || policy.getId() == null) {
            throw new Exception("Póliza y su ID son requeridos para actualizar");
        }

        // Validar que la póliza existe
        Policy existing = policyPort.findById(policy);
        if (existing == null) {
            throw new Exception("Póliza no encontrada con id: " + policy.getId());
        }

        // Actualizar póliza
        policyPort.update(policy);
    }
}
