
package app.domain.model;


public class Invoice extends Person {
    private long id;
    private Person doctor;
    private Person patient;
    private Policy policy;
    private double amount; 

public Invoice(Person patient, Person doctor, Policy policy, double amount) {
    this.patient = patient;
    this.doctor = doctor;
    this.policy = policy;
    this.amount = amount;
}

    
    @Override
    public long getId() {
        return id;
    }

    @Override
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

    @Override
    public String toString() {
        
        return "Factura{id=" + id 
                + ", paciente=" + (patient != null ? patient.getName() : "N/A") 
                + ", doctor=" + (doctor != null ? doctor.getName() : "N/A") 
                + ", valor=" + amount 
                + "}";
    }
}
