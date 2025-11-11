
package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medical_orders")
public class MedicalOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private String observations;

    // Relación con historia clínica (clave foránea)
    @ManyToOne
    @JoinColumn(name = "medical_history_id")
    private MedicalHistoryEntity medicalHistory;

    // Relación con paciente (si existe tabla PatientEntity)
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)
    private PatientEntity patient;

    @OneToMany(mappedBy = "medicalOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcedureEntity> procedures;

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public MedicalHistoryEntity getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistoryEntity medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public List<ProcedureEntity> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureEntity> procedures) {
        this.procedures = procedures;
    }
}
