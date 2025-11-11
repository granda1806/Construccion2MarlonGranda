package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vital_signs")
public class VitalSignsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodPressure;      // Ejemplo: "120/80"
    private double temperature;        // Ejemplo: 36.5
    private int pulse;                 // Ejemplo: 72
    private int bloodOxygenLevel;      // Ejemplo: 98 (%)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_order_id")
    private MedicalOrderEntity medicalOrder;

    // Constructor vacío requerido por JPA
    public VitalSignsEntity() {}

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getPulse() {
        return pulse;
    }

    public int getBloodOxygenLevel() {
        return bloodOxygenLevel;
    }

    public MedicalOrderEntity getMedicalOrder() {
        return medicalOrder;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public void setBloodOxygenLevel(int bloodOxygenLevel) {
        this.bloodOxygenLevel = bloodOxygenLevel;
    }

    public void setMedicalOrder(MedicalOrderEntity medicalOrder) {
        this.medicalOrder = medicalOrder;
    }

    
}
