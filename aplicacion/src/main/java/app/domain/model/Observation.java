package app.domain.model;

public class Observation {
    private String notes;

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "Observaciones: " + notes;
    }
}
