package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
<<<<<<< HEAD
import java.sql.Date;

@Entity
@Table(name = "medical_history")
=======
import java.util.Date;

@Entity
@Table(name = "medical_histories")
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
public class MedicalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private Date date;

    private String reason;
    private String symptoms;
    private String diagnosis;

    // --- Getters y Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
=======
    private Long id;

    // ✅ Usamos Date correctamente con @Temporal
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(length = 500)
    private String observations;

    @Column(name = "patient_document", nullable = false)
    private String patientDocument; // ✅ En el mapper se maneja como String

    @Column(name = "reason_for_consultation", length = 300)
    private String reasonForConsultation;

    @Column(length = 500)
    private String symptoms;

    @Column(length = 500)
    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    // ======= Constructores =======
    public MedicalHistoryEntity() {
    }

    public MedicalHistoryEntity(Date date, String observations, String patientDocument,
                                String reasonForConsultation, String symptoms,
                                String diagnosis, DoctorEntity doctor) {
        this.date = date;
        this.observations = observations;
        this.patientDocument = patientDocument;
        this.reasonForConsultation = reasonForConsultation;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.doctor = doctor;
    }

    // ======= Getters y Setters =======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(String patientDocument) {
        this.patientDocument = patientDocument;
    }

    public String getReasonForConsultation() {
        return reasonForConsultation;
    }

    public void setReasonForConsultation(String reasonForConsultation) {
        this.reasonForConsultation = reasonForConsultation;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
}
