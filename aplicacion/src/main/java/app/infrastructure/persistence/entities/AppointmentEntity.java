package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Medical_Appointment")
public class AppointmentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación con el administrador (User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private UserEntity admin;

    // Relación con el paciente (Patient)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @Column(nullable = false)
    private Date date;

    // --- Constructores ---
    public AppointmentEntity() {}

    public AppointmentEntity(Integer id, UserEntity admin, PatientEntity patient, Date date)
    {
        
        this.id = id;
        this.admin = admin;
        this.patient = patient;
        this.date = date;
        
    }

    // --- Getters y Setters ---
    public Integer getId()
    {
        
        return id;
        
    }

    public void setId(Integer id)
    {
        
        this.id = id;
        
    }

    public UserEntity getAdmin()
    {
        
        return admin;
        
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient)
    {
        
        this.patient = patient;
        
    }

    public Date getDate()
    {
        
        return date;
        
    }

    public void setDate(Date date)
    {
        
        this.date = date;
        
    }
    
}
