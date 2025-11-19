package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medical_orders")
public class MedicalOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private String observations;
    private LocalDateTime createdAt;

    @Column(name = "patient_document", nullable = true)
    private Long patientDocument;

    // Relación con historia clínica (clave foránea)
    @ManyToOne
    @JoinColumn(name = "medical_history_id")
    private MedicalHistoryEntity medicalHistory;

    // Relación con paciente (si existe tabla PatientEntity)
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)
    private PatientEntity patient;

    // Relación con el doctor que creó la orden
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private DoctorEntity doctor;

    @OneToMany(mappedBy = "medicalOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcedureEntity> procedures;

    @OneToMany(mappedBy = "medicalOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionEntity> prescriptions;

    @OneToMany(mappedBy = "medicalOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiagnosticTestEntity> diagnosticTests;

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
        if (orderNumber == null) {
            this.orderNumber = null;
            return;
        }
        // Allow only digits, maximum 6 characters
        if (!orderNumber.matches("^\\d{1,6}$")) {
            throw new IllegalArgumentException("El número de orden debe contener sólo dígitos y máximo 6 caracteres.");
        }
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(Long patientDocument) {
        this.patientDocument = patientDocument;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public List<PrescriptionEntity> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<PrescriptionEntity> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<DiagnosticTestEntity> getDiagnosticTests() {
        return diagnosticTests;
    }

    public void setDiagnosticTests(List<DiagnosticTestEntity> diagnosticTests) {
        this.diagnosticTests = diagnosticTests;
    }
}
