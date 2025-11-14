
package app.adapter.in.builder;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Patient;
import app.domain.model.Policy;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.domain.model.enums.TypePolicy;

@Component
public class PolicyBuilder {
    
    private final UserValidator userValidator;
    
    @Autowired
    public PolicyBuilder(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
    
    public Policy policyBuilder(String documentAdmin, String documentPatient, String policyNumber, String policyStatus) throws Exception{
        User admin = new User();
        Patient patient = new Patient();
        
        Policy policy = new Policy();
        admin.setDocument(userValidator.documentValidator(documentAdmin));
        patient.setDocument(userValidator.documentValidator(documentPatient));
        policy.setPolicyNumber(userValidator.policyNumber(policyNumber));
        policy.setPolicyStatus(userValidator.policyStatus(policyStatus));
        
        policy.setAdmin(admin);
        policy.setPatient(patient);
        return policy;
    }
}
