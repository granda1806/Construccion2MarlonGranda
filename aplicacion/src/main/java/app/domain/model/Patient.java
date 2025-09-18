package app.domain.model;

public class Patient {
    private int id;
    private String name;

    
    public Patient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Actualizar desde otro paciente
    public void updateFrom(Patient other) {
        if (other.getName() != null) {
            this.name = other.getName();
        }
    }

    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + "'}";
    }
}
