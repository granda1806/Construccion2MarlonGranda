
package app.application.usecases;

import app.domain.model.Policy;
import app.domain.model.enums.TypePolicy;
import app.domain.services.CreatePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PolicyUseCase {
    
    @Autowired
    private CreatePolicy createPolicy;
    
    public void createARLSura(Policy policy)throws Exception {
        policy.setPolicyName(TypePolicy.ARLSura);
        createPolicy.create(policy);
    }
    
    public void cratePositivaSeguros(Policy policy) throws Exception {
        policy.setPolicyName(TypePolicy.PositivaSeguros);
        createPolicy.create(policy);
    }
    
    public void crateEPSSura(Policy policy) throws Exception {
        policy.setPolicyName(TypePolicy.EPSSura);
        createPolicy.create(policy);
    }
    
    public void crateEPSSanitas(Policy policy) throws Exception {
        policy.setPolicyName(TypePolicy.EPSSanitas);
        createPolicy.create(policy);
    }
    
    public void crateCoomeva(Policy policy) throws Exception {
        policy.setPolicyName(TypePolicy.Coomeva);
        createPolicy.create(policy);
    }
}
