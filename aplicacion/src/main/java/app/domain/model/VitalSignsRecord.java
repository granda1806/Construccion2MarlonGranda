package app.domain.model;

public class VitalSignsRecord {
    private String patientId;
    private String bloodPressure;
    private String temperature;
    private String pulse;
    private String bloodOxygenLevel;
    
    public void showData() {
        System.out.println("\nSignos Vitales");
        System.out.println("ID Paciente: " + patientId);
        System.out.println("Presión arterial: " + bloodPressure);
        System.out.println("Temperatura: " + temperature);
        System.out.println("Pulso: " + pulse);
        System.out.println("Oxígeno en sangre: " + bloodOxygenLevel);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getBloodOxygenLevel() {
        return bloodOxygenLevel;
    }

    public void setBloodOxygenLevel(String bloodOxygenLevel) {
        this.bloodOxygenLevel = bloodOxygenLevel;
    }

    
}