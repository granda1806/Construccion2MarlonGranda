package app.infrastructure.persistence.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "observation")
public class ObservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientId;
    private String nurseId;
    private String observationText;
    private String date;

    public Long getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public String getObservationText() {
        return observationText;
    }

    public String getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public void setObservationText(String observationText) {
        this.observationText = observationText;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
