package app.application.usecases;

import app.domain.model.Patient;
import app.domain.model.Policy;
import app.domain.model.enums.TypePolicy;
import app.domain.ports.PolicyPort;
import app.domain.ports.UserPortPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchPolicyUseCase {

    @Autowired
    private PolicyPort policyPort;

    @Autowired
    private UserPortPatient userPortPatient;

    /**
     * Buscar póliza por ID
     */
    public Policy findPolicyById(Long id) throws Exception {
        Policy policy = new Policy();
        policy.setId(id);
        Policy found = policyPort.findById(policy);
        if (found == null) {
            throw new Exception("Póliza no encontrada con id: " + id);
        }
        return found;
    }

    /**
     * Buscar todas las pólizas de un paciente específico
     */
    public List<Policy> findPoliciesByPatient(Long patientId) throws Exception {
        Patient patient = new Patient();
        patient.setId(patientId);

        // Validar que el paciente exista
        Patient existingPatient = userPortPatient.findByDocument(patient);
        if (existingPatient == null) {
            throw new Exception("Paciente no encontrado con id: " + patientId);
        }

        // Buscar pólizas del paciente
        List<Policy> policies = policyPort.findByPatient(patient);
        if (policies == null) {
            policies = new ArrayList<>();
        }

        return policies;
    }

    /**
     * Buscar pólizas activas de un paciente
     */
    public List<Policy> findActivePoliciesByPatient(Long patientId) throws Exception {
        List<Policy> allPolicies = findPoliciesByPatient(patientId);
        List<Policy> activePolicies = new ArrayList<>();

        for (Policy policy : allPolicies) {
            if (policy.isPolicyStatus()) {
                activePolicies.add(policy);
            }
        }

        return activePolicies;
    }

    /**
     * Buscar pólizas inactivas de un paciente
     */
    public List<Policy> findInactivePoliciesByPatient(Long patientId) throws Exception {
        List<Policy> allPolicies = findPoliciesByPatient(patientId);
        List<Policy> inactivePolicies = new ArrayList<>();

        for (Policy policy : allPolicies) {
            if (!policy.isPolicyStatus()) {
                inactivePolicies.add(policy);
            }
        }

        return inactivePolicies;
    }

    /**
     * Buscar pólizas de un tipo específico (EPS, ARL, etc)
     */
    public List<Policy> findPoliciesByType(TypePolicy type) throws Exception {
        if (type == null) {
            throw new Exception("Tipo de póliza no puede ser nulo");
        }

        List<Policy> policies = policyPort.findByPolicyType(type);
        if (policies == null) {
            policies = new ArrayList<>();
        }

        return policies;
    }

    /**
     * Buscar pólizas de un paciente específico por tipo de póliza
     */
    public List<Policy> findPoliciesByPatientAndType(Long patientId, TypePolicy type) throws Exception {
        List<Policy> allPolicies = findPoliciesByPatient(patientId);
        List<Policy> filteredPolicies = new ArrayList<>();

        for (Policy policy : allPolicies) {
            if (policy.getPolicyName() != null && policy.getPolicyName().equals(type)) {
                filteredPolicies.add(policy);
            }
        }

        return filteredPolicies;
    }

    /**
     * Buscar póliza por número de póliza
     */
    public Policy findPolicyByNumber(Long policyNumber) throws Exception {
        if (policyNumber == null || policyNumber <= 0) {
            throw new Exception("Número de póliza inválido");
        }

        List<Policy> allPolicies = policyPort.findByPolicyNumber(policyNumber);
        if (allPolicies == null || allPolicies.isEmpty()) {
            throw new Exception("Póliza no encontrada con número: " + policyNumber);
        }

        return allPolicies.get(0);
    }
}
