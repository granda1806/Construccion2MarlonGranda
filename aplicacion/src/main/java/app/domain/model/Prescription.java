
package app.domain.model;

/**
 * Representa una prescripción (medicamento) dentro de una historia clínica.
 */
public class Prescription {

    private String orderNumber; // hasta 6 dígitos (string para permitir ceros iniciales)
    private String medicineId;
    private String dose;
    private String duration;
    private int item; // número dentro de la orden

    public Prescription() {
    }

    public Prescription(String orderNumber, String medicineId, String dose, String duration, int item) {
        setOrderNumber(orderNumber);
        this.medicineId = medicineId;
        this.dose = dose;
        this.duration = duration;
        this.item = item;
    }

    // Getters / Setters
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        if (orderNumber == null) {
            this.orderNumber = null;
            return;
        }
        if (!orderNumber.matches("^\\d{1,6}$")) {
            throw new IllegalArgumentException("El número de orden debe contener sólo dígitos y máximo 6 caracteres.");
        }
        this.orderNumber = orderNumber;
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
}
