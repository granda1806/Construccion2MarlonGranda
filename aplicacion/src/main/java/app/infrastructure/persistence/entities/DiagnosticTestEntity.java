package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnostic_tests")
public class DiagnosticTestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testId;
    private int item;

    // 🔹 RELACIÓN CON ORDEN MÉDICA
    @ManyToOne
    @JoinColumn(name = "medical_order_id")
    private MedicalOrderEntity medicalOrder;

    // --- Constructores ---
    public DiagnosticTestEntity() {
    }

    public DiagnosticTestEntity(String testId, int item, MedicalOrderEntity medicalOrder) {
        this.testId = testId;
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

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
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
