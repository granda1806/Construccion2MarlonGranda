package app.infrastructure.persistence.entities;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private Long document;

    @Column(name = "age")
    private int age;

    @Column(length = 20)
    private String gender;

    @Column(length = 100)
    private String address;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 50)
    private String email;

    @Column(name = "emergency_contact_name", length = 50)
    private String emergencyContactName;

    @Column(name = "emergency_contact_number", length = 20)
    private String emergencyContactNumber;

    @Column(name = "relationship_patient", length = 30)
    private String relationshipPatient;

    public PatientEntity() {
    }

    public PatientEntity(
            Long id,
            String name,
            Long document,
            String gender,
            String address,
            String phoneNumber,
            String email,
            String emergencyContactName,
            String emergencyContactNumber,
            String relationshipPatient) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.relationshipPatient = relationshipPatient;
    }

    // === Getters y Setters ===
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getRelationshipPatient() {
        return relationshipPatient;
    }

    public void setRelationshipPatient(String relationshipPatient) {
        this.relationshipPatient = relationshipPatient;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}