package app.infrastructure.persistence.entities;

import app.domain.model.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_complete", nullable = false, length = 100)
    private String nameComplete;

    @Column(name = "lastname_complete", nullable = false, length = 100)
    private String lastnameComplete;

    @Column(unique = true, nullable = false)
    private Long document;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(unique = true, nullable = false, length = 50)
    private String userName;

    @Column(nullable = false)
    private String password;

    public UserEntity() {
    }

    public UserEntity(Long id, String nameComplete, String lastnameComplete, Long document, int age, Role role, String userName, String password) {
        this.id = id;
        this.nameComplete = nameComplete;
        this.lastnameComplete = lastnameComplete;
        this.document = document;
        this.age = age;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNameComplete() {
        return nameComplete;
    }
    public void setNameComplete(String nameComplete) {
        this.nameComplete = nameComplete;
    }

    public String getLastnameComplete() {
        return lastnameComplete;
    }
    public void setLastnameComplete(String lastnameComplete) {
        this.lastnameComplete = lastnameComplete;
    }

    public Long getDocument() {
        return document;
    }
    public void setDocument(Long document) {
        this.document = document;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
