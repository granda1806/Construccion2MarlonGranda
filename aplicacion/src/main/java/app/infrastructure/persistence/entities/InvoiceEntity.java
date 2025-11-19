package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Información básica
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "patient_age")
    private int patientAge;

    @Column(name = "patient_document")
    private Long patientDocument;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "doctor_name")
    private String doctorName;

    // Información de la póliza
    @Column(name = "policy_id")
    private Long policyId;

    @Column(name = "insurance_company")
    private String insuranceCompany;

    @Column(name = "policy_number")
    private Long policyNumber;

    @Column(name = "policy_validity_days")
    private int policyValidityDays;

    @Column(name = "policy_end_date")
    private java.sql.Date policyEndDate;

    // Monto total

    @Column(name = "amount")
    private double amount;

    @Column(name = "total")
    private Double total;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Column(name = "copay")
    private double copay;

    @Column(name = "insurance_covered")
    private double insuranceCovered;

    @Column(name = "patient_copay")
    private Double patientCopay;

    public Double getPatientCopay() {
        return patientCopay;
    }

    public void setPatientCopay(Double patientCopay) {
        this.patientCopay = patientCopay;
    }

    @Column(name = "insurer_share")
    private Double insurerShare;

    public InvoiceEntity() {
    }

    public Double getInsurerShare() {
        return insurerShare;
    }

    public void setInsurerShare(Double insurerShare) {
        this.insurerShare = insurerShare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public Long getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(Long patientDocument) {
        this.patientDocument = patientDocument;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public Long getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Long policyNumber) {
        this.policyNumber = policyNumber;
    }

    public int getPolicyValidityDays() {
        return policyValidityDays;
    }

    public void setPolicyValidityDays(int policyValidityDays) {
        this.policyValidityDays = policyValidityDays;
    }

    public java.sql.Date getPolicyEndDate() {
        return policyEndDate;
    }

    public void setPolicyEndDate(java.sql.Date policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCopay() {
        return copay;
    }

    public void setCopay(double copay) {
        this.copay = copay;
    }

    public double getInsuranceCovered() {
        return insuranceCovered;
    }

    public void setInsuranceCovered(double insuranceCovered) {
        this.insuranceCovered = insuranceCovered;
    }
}
