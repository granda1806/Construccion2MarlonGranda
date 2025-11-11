
package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medication")
public class MedicationEntity {

    @Id
    @Column(length = 50)
    private String id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private double cost;

    public MedicationEntity() {
    }

    public MedicationEntity(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    // Getters / Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
