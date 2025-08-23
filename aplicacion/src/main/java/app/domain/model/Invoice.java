
package app.domain.model;


public class Invoice extends Person {
    private long id;
    private Person Doctor;
    private Person Patient;
    private Policy policy;
    
    public Invoice(Person Patient, Person Doctor, Policy Policy) {
        this.Patient = Patient;
        this.Doctor = Doctor;
        this.policy = Policy;
    } 
}
