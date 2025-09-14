package app.domain.model;

public class EmergencyContact {
    private int id;
    private String name;
    private String phone;

    public EmergencyContact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        
        return "ContactoEmergencia{id=" + id + ", nombre='" + name + "', telefono='" + phone + "'}";
    }
}
