
package app.domain.services;

import app.domain.model.Patient;
import app.domain.model.Policy;
import app.domain.model.User;
import app.domain.model.enums.Role;
import app.domain.ports.PolicyPort;
import app.domain.ports.UserPortOut;
import app.domain.ports.UserPortPatient;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePolicy {
    
    @Autowired
    private UserPortOut userPort;
    @Autowired
    private UserPortPatient portPatient;
    @Autowired
    private PolicyPort policyPort;
    
    public void create(Policy policy) throws Exception {
        User admin = userPort.findByDocument(policy.getAdmin());
        if (admin == null || !admin.getRole().equals(Role.ADMIN)) {
            throw new Exception("El administrador no existe o el usuario no es un administrador.");
        }
        Patient patient = portPatient.findByDocument(policy.getPatient());
        if (patient == null) {
            throw new Exception("El paciente no existe...");
        }
        policy.setPolicyTerminationDate(new Date(System.currentTimeMillis()));
        policy.setPatient(patient);
        policy.setAdmin(admin);
        
        policyPort.save(policy);
    }
}
