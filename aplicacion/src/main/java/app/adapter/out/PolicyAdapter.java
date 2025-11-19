
package app.adapter.out;

import app.domain.model.Policy;
import app.domain.ports.PolicyPort;
import app.infrastructure.persistence.entities.PolicyEntity;
import app.infrastructure.persistence.mapper.PolicyMapper;
import app.infrastructure.persistence.repository.PolicyRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyAdapter implements PolicyPort {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public Policy findById(Policy policy) throws Exception {
        Optional<PolicyEntity> policyEntity = policyRepository.findById(policy.getId());
        if (policyEntity.isPresent()) {
            return PolicyMapper.toDomain(policyEntity.get());
        }
        return null;
    }

    @Override
    public void save(Policy policy) throws Exception {
        PolicyEntity entity = PolicyMapper.toEntity(policy);
        if (entity != null) {
            policyRepository.save(entity);
        }
        System.out.println("El administrador " + policy.getAdmin().getNameComplete() +
                " ha asignado la poliza del paciente " + policy.getPatient().getNameComplete() + " con estado: "
                + policy.isPolicyStatus());
    }

    @Override
    public java.util.List<Policy> findByPatient(app.domain.model.Patient patient) throws Exception {
        java.util.List<PolicyEntity> entities = policyRepository.findByPatientId(patient.getId());
        java.util.List<Policy> policies = new java.util.ArrayList<>();
        for (PolicyEntity entity : entities) {
            policies.add(PolicyMapper.toDomain(entity));
        }
        return policies;
    }

    @Override
    public java.util.List<Policy> findByPolicyType(app.domain.model.enums.TypePolicy type) throws Exception {
        java.util.List<PolicyEntity> entities = policyRepository.findByNamePolicy(type);
        java.util.List<Policy> policies = new java.util.ArrayList<>();
        for (PolicyEntity entity : entities) {
            policies.add(PolicyMapper.toDomain(entity));
        }
        return policies;
    }

    @Override
    public java.util.List<Policy> findByPolicyNumber(Long policyNumber) throws Exception {
        java.util.List<PolicyEntity> entities = policyRepository.findByPolicyNumber(policyNumber);
        java.util.List<Policy> policies = new java.util.ArrayList<>();
        for (PolicyEntity entity : entities) {
            policies.add(PolicyMapper.toDomain(entity));
        }
        return policies;
    }

    @Override
    public java.util.List<Policy> findAllActivePolicies() throws Exception {
        java.util.List<PolicyEntity> entities = policyRepository.findByPolicyStatusTrue();
        java.util.List<Policy> policies = new java.util.ArrayList<>();
        for (PolicyEntity entity : entities) {
            policies.add(PolicyMapper.toDomain(entity));
        }
        return policies;
    }

    @Override
    public void update(Policy policy) throws Exception {
        PolicyEntity entity = PolicyMapper.toEntity(policy);
        if (entity != null) {
            policyRepository.save(entity);
        }
    }

    @Override
    public void delete(Long policyId) throws Exception {
        if (policyId != null) {
            policyRepository.deleteById(policyId);
        }
    }

}
