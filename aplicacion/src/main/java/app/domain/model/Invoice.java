
package app.domain.model;

public class Invoice {

    private long id;
    private Person doctor;
    private Person patient;
    private Policy policy;
    private double amount;
    private double copay;
    private double insuranceCovered;

    public Invoice(Person patient, Person doctor, Policy policy, double amount) {
        this.patient = patient;
        this.doctor = doctor;
        this.policy = policy;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getDoctor() {
        return doctor;
    }

    public void setDoctor(Person doctor) {
        this.doctor = doctor;
    }

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
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

    @Override
    public String toString() {
        return "Factura{id=" + id
                + ", paciente=" + (patient != null ? patient.getNameComplete() : "N/A")
                + ", doctor=" + (doctor != null ? doctor.getNameComplete() : "N/A")
                + ", valor=" + amount
                + ", copago=" + copay
                + ", aseguradora=" + insuranceCovered
                + "}";
    }
}
