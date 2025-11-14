
package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import app.domain.model.enums.TypePolicy;
import java.sql.Date;

@Entity
@Table(name="Policy")
public class PolicyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // Relación con el administrador (User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private UserEntity admin;

    // Relación con el paciente (Patient)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePolicy namePolicy;
    
    @Column(unique = true, nullable = false, length = 50)
    private Long policyNumber;
    
    @Column(unique = false, nullable = false)
    private boolean policyStatus;
    
    @Column(unique = false, nullable = true)
    private Date policyTerminationDate;
    
    public PolicyEntity(){
    
    }

    public PolicyEntity(Integer id, UserEntity admin, PatientEntity patient, TypePolicy namePolicy, Long policyNumber, boolean policyStatus, Date policyTerminationDate) {
        this.id = id;
        this.admin = admin;
        this.patient = patient;
        this.namePolicy = namePolicy;
        this.policyNumber = policyNumber;
        this.policyStatus = policyStatus;
        this.policyTerminationDate = policyTerminationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public TypePolicy getNamePolicy() {
        return namePolicy;
    }

    public void setNamePolicy(TypePolicy namePolicy) {
        this.namePolicy = namePolicy;
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
