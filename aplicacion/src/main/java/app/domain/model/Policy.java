
package app.domain.model;

import app.domain.model.enums.TypePolicy;
import java.sql.Date;

public class Policy
{
    
    private Integer id;
    private User admin;
    private Patient patient;
    private TypePolicy policyName;
    private Long policyNumber;
    private boolean policyStatus;
    private Date policyTerminationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TypePolicy getPolicyName() {
        return policyName;
    }

    public void setPolicyName(TypePolicy policyName) {
        this.policyName = policyName;
    }

    public Long getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Long policyNumber) {
        this.policyNumber = policyNumber;
    }

    public boolean isPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(boolean policyStatus) {
        this.policyStatus = policyStatus;
    }

    public Date getPolicyTerminationDate() {
        return policyTerminationDate;
    }

    public void setPolicyTerminationDate(Date policyTerminationDate) {
        this.policyTerminationDate = policyTerminationDate;
    }

    
 
}
