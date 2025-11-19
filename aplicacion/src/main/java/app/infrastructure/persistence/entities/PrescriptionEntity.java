package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicineId;
    private String dose;
    private String duration;
    private int item;

    // 🔹 RELACIÓN CON ORDEN MÉDICA
    @ManyToOne
    @JoinColumn(name = "medical_order_id")
    private MedicalOrderEntity medicalOrder;

    // --- Constructores ---
    public PrescriptionEntity() {
    }

    public PrescriptionEntity(String medicineId, String dose, String duration, int item,
            MedicalOrderEntity medicalOrder) {
        this.medicineId = medicineId;
        this.dose = dose;
        this.duration = duration;
        this.item = item;
        this.medicalOrder = medicalOrder;
    }

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public MedicalOrderEntity getMedicalOrder() {
        return medicalOrder;
    }

    public void setMedicalOrder(MedicalOrderEntity medicalOrder) {
        this.medicalOrder = medicalOrder;
    }
}
