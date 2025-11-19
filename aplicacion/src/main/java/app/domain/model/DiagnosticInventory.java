package app.domain.model;

public class DiagnosticInventory {
    private String id; // Identificador único del inventario
    private String diagnosticType; // Tipo de diagnóstico
    private int counter; // Contabilizador de exámenes disponibles
    private boolean available; // Disponibilidad de herramientas
    private String toolDescription; // Descripción de la herramienta
    private String location; // Ubicación física o virtual
    private String notes; // Observaciones adicionales

    public DiagnosticInventory(String id, String diagnosticType, int counter, boolean available, String toolDescription,
            String location, String notes) {
        this.id = id;
        this.diagnosticType = diagnosticType;
        this.counter = counter;
        this.available = available;
        this.toolDescription = toolDescription;
        this.location = location;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnosticType() {
        return diagnosticType;
    }

    public void setDiagnosticType(String diagnosticType) {
        this.diagnosticType = diagnosticType;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Incrementa el contador del inventario en la cantidad indicada.
     * 
     * @param amount cantidad a incrementar (debe ser mayor que 0)
     */
    public void increaseCounter(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La cantidad a incrementar debe ser mayor que 0.");
        }
        this.counter += amount;
        this.available = this.counter > 0;
    }

    /**
     * Decrementa el contador del inventario en la cantidad indicada.
     * Lanza IllegalArgumentException si la cantidad es inválida o no hay
     * suficientes unidades.
     * 
     * @param amount cantidad a decrementar (debe ser mayor que 0)
     */
    public void decreaseCounter(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La cantidad a decrementar debe ser mayor que 0.");
        }
        if (this.counter - amount < 0) {
            throw new IllegalArgumentException(
                    "No hay suficientes unidades en el inventario para decrementar la cantidad solicitada.");
        }
        this.counter -= amount;
        this.available = this.counter > 0;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getToolDescription() {
        return toolDescription;
    }

    public void setToolDescription(String toolDescription) {
        this.toolDescription = toolDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
