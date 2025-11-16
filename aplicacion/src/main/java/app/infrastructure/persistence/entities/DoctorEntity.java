package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long document;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 100)
    private String specialty;

    @Column(name = "license_number", nullable = false, unique = true, length = 50)
    private String licenseNumber;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    /**
     * Relación con historiales médicos (opcional, se puede dejar sin mappedBy
     * si no está definido en la otra clase)
     *
     * Si en MedicalHistoryEntity tienes algo como:
     *
     * @ManyToOne
     * @JoinColumn(name = "doctor_id") private DoctorEntity doctor;
     *
     * entonces puedes mantener "mappedBy = doctor"
     */
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalHistoryEntity> medicalHistories = new ArrayList<>();

    // --- Getters y Setters ---
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<MedicalHistoryEntity> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(List<MedicalHistoryEntity> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }

    public Long getDocumentNumber() {
        return document;
    }

    public void setDocumentNumber(Long document) {
        this.document = document;
    }
}
