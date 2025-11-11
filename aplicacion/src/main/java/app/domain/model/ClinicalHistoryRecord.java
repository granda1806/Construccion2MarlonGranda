
package app.domain.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase de dominio que representa el registro de la historia clínica.
 * No mapea relaciones JPA con Prescription ni Procedure,
 * ya que son clases de dominio no persistentes.
 */
public class ClinicalHistoryRecord {

    private Long id; // ID autogenerado por MySQL (si aplica en DTO)
    private LocalDate date;
    private Long doctorId;
    private Long patientDocument;
    private String reasonForConsultation;
    private String symptoms;
    private String diagnosis;

    // Listas simples, sin anotaciones JPA
    private List<Prescription> prescriptions;
    private List<Procedure> procedures;

    // ==========================
    // Constructores
    // ==========================
    public ClinicalHistoryRecord() {
    }

    public ClinicalHistoryRecord(LocalDate date, Long doctorId, Long patientDocument,
            String reasonForConsultation, String symptoms, String diagnosis,
            List<Prescription> prescriptions, List<Procedure> procedures) {
        this.date = date;
        this.doctorId = doctorId;
        this.patientDocument = patientDocument;
        this.reasonForConsultation = reasonForConsultation;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.prescriptions = prescriptions;
        this.procedures = procedures;
    }

    // ==========================
    // Getters & Setters
    // ==========================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(Long patientDocument) {
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

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
