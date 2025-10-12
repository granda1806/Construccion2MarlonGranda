
package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Patient")
public class PatientEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = true)
    private Long document;
    
    @Column(nullable = false, length = 100)
    private String date;
    
    @Column(nullable = false, length = 50)
    private String gender;
    
    @Column(nullable = false, length = 150)
    private String address;
    
    @Column(nullable = true, length = 10)
    private Long phoneNumber;
    
    @Column(nullable = true, length = 100)
    private String Email;
    
    @Column(nullable = false, length = 100)
    private String emergencyContactName;
    
    @Column(nullable = false, length = 10)
    private Long emergencyContactNumber;
    
    @Column(nullable = false, length = 100)
    private String relationshipPatient;
    
    public PatientEntity() {
    }

    public PatientEntity(Long id, String name, Long document, String date, String gender, String address, Long phoneNumber,
            String Email, String emergencyContactName, Long emergencyContactNumber, String relationshipPatient) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.Email = Email;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.relationshipPatient = relationshipPatient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public Long getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(Long emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getRelationshipPatient() {
        return relationshipPatient;
    }

    public void setRelationshipPatient(String relationshipPatient) {
        this.relationshipPatient = relationshipPatient;
    }
}
