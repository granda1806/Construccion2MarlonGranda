package app.infrastructure.persistence.entities;

public class DiagnosticInventoryEntity {
    private String id;

    private String diagnosticType;

    private int counter;

    private boolean available;

    private String toolDescription;

    private String location;

    private String notes;

    // Constructor vacío requerido por JPA
    public DiagnosticInventoryEntity() {
    }

    public DiagnosticInventoryEntity(String diagnosticType, int counter, boolean available, String toolDescription,
            String location, String notes) {
        this.diagnosticType = diagnosticType;
        this.counter = counter;
        this.available = available;
        this.toolDescription = toolDescription;
        this.location = location;
        this.notes = notes;
    }

    // Getters y setters
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
