package app.domain.estructure.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private String name;

    public UserEntity() {}

    public UserEntity(Long id, String document, String name) {
        this.id = id;
        this.document = document;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDocument() { return document; }
    public void setDocument(String document) { this.document = document; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
