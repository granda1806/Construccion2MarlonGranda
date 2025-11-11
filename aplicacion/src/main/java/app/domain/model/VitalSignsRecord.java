
package app.domain.model;

/**
 * Modelo de dominio para representar un registro de signos vitales.
 * No contiene anotaciones JPA ni dependencias de infraestructura.
 */
public class VitalSignsRecord {

    private Long id;
    private String bloodPressure;    // Ejemplo: "120/80"
    private double temperature;      // Ejemplo: 36.5
    private int pulse;               // Ejemplo: 72
    private int bloodOxygenLevel;    // Ejemplo: 98 (%)
    private Long medicalOrderId;     // Relación con la orden médica

    public VitalSignsRecord() {}

    public VitalSignsRecord(Long id, String bloodPressure, double temperature,
                            int pulse, int bloodOxygenLevel, Long medicalOrderId) {
        this.id = id;
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.pulse = pulse;
        this.bloodOxygenLevel = bloodOxygenLevel;
        this.medicalOrderId = medicalOrderId;
    }

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

    public Long getMedicalOrderId() {
        return medicalOrderId;
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

    public void setMedicalOrderId(Long medicalOrderId) {
        this.medicalOrderId = medicalOrderId;
    }
    
}    