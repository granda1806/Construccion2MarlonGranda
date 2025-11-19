
package app.domain.model;

/**
 * Representa un procedimiento médico o ayuda diagnóstica que forma parte de una
 * orden médica.
 */
public class Procedure {

    private String orderNumber; // Número de la orden médica
    private String procedureId; // ID del procedimiento
    private int quantity; // Cantidad de veces que se realiza
    private String frequency; // Frecuencia (ej. "1 vez por semana")
    private double cost; // Costo asociado
    private boolean requiresSpecialist; // Indica si requiere especialista
    private String specialistId; // ID o tipo del especialista
    private int item; // Número de ítem dentro de la orden

    public Procedure() {
    }

    public Procedure(String orderNumber, String procedureId, int quantity,
            String frequency, double cost, boolean requiresSpecialist,
            String specialistId, int item) {
        this.orderNumber = orderNumber;
        this.procedureId = procedureId;
        this.quantity = quantity;
        this.frequency = frequency;
        this.cost = cost;
        this.requiresSpecialist = requiresSpecialist;
        this.specialistId = specialistId;
        this.item = item;
    }

    // ===== Getters y Setters =====
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        if (orderNumber != null && orderNumber.matches("^\\d{1,6}$")) {
            this.orderNumber = orderNumber;
        } else {
            throw new IllegalArgumentException("El número de orden debe tener máximo 6 dígitos.");
        }
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        this.quantity = quantity;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isRequiresSpecialist() {
        return requiresSpecialist;
    }

    public void setRequiresSpecialist(boolean requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    /**
     * Consume (decrementa) la cantidad pedida desde el inventario asociado.
     * Lanza IllegalArgumentException si el inventario es nulo o no tiene
     * suficientes unidades.
     */
    public void consumeFromInventory(DiagnosticInventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Inventario nulo.");
        }
        if (this.quantity <= 0) {
            throw new IllegalArgumentException("La cantidad a consumir debe ser mayor que 0.");
        }
        inventory.decreaseCounter(this.quantity);
    }

    /**
     * Libera (incrementa) la cantidad al inventario asociado.
     */
    public void releaseToInventory(DiagnosticInventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Inventario nulo.");
        }
        if (this.quantity <= 0) {
            throw new IllegalArgumentException("La cantidad a liberar debe ser mayor que 0.");
        }
        inventory.increaseCounter(this.quantity);
    }

    // ===== toString() =====
    @Override
    public String toString() {
        return "Procedure{"
                + "orderNumber='" + orderNumber + '\''
                + ", procedureId='" + procedureId + '\''
                + ", quantity=" + quantity
                + ", frequency='" + frequency + '\''
                + ", cost=" + cost
                + ", requiresSpecialist=" + requiresSpecialist
                + ", specialistId='" + (specialistId != null ? specialistId : "No requerido") + '\''
                + ", item=" + item
                + '}';
    }
}
