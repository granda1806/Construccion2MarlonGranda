
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

    public Policy policyBuilder(String documentAdmin, String documentPatient, String policyNumber, String policyStatus,
            java.sql.Date policyStartDate) throws Exception {
        User admin = new User();
        Patient patient = new Patient();

        Policy policy = new Policy();
        admin.setDocument(userValidator.documentValidator(documentAdmin));
        patient.setDocument(userValidator.documentValidator(documentPatient));
        policy.setPolicyNumber(userValidator.policyNumber(policyNumber));
        policy.setPolicyStatus(userValidator.policyStatus(policyStatus));
        policy.setPolicyStartDate(policyStartDate);

        // Calcular fecha de finalización automáticamente (1 año después de inicio)
        if (policyStartDate != null) {
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(policyStartDate);
            cal.add(java.util.Calendar.YEAR, 1);
            policy.setPolicyTerminationDate(new java.sql.Date(cal.getTimeInMillis()));
        }

        policy.setAdmin(admin);
        policy.setPatient(patient);
        return policy;
    }
}
