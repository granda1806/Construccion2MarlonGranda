
package app.adapter.out;

import app.domain.model.Policy;
import app.domain.ports.PolicyPort;
import app.infrastructure.persistence.entities.PolicyEntity;
import app.infrastructure.persistence.mapper.PolicyMapper;
import app.infrastructure.persistence.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyAdapter implements PolicyPort{
    
    @Autowired
    private PolicyRepository policyRepository;
    
    @Override
    public Policy findById(Policy policy) throws Exception {
        PolicyEntity policyEntity = policyRepository.findById(policy.getId());
        return PolicyMapper.toDomain(policyEntity);
    }

    
    @Override
    public void save(Policy policy) throws Exception{
        policyRepository.save(PolicyMapper.toEntity(policy));
        System.out.println("El administrador " + policy.getAdmin().getNameComplete() +
                " ha asignado la poliza del paciente" + policy.getPatient().getNameComplete() + " con estado: " + policy.isPolicyStatus());  
    }
    
}
