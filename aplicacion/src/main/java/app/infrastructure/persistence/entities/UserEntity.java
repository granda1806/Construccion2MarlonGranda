package app.infrastructure.persistence.entities;

import app.domain.model.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // ✅ lo genera la BD, no se setea en constructor

    @Column(nullable = false, unique = true)
    private Long document;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String user;

    @Column(nullable = false)
    private String password;  // ⚠️ No debería ser unique, puedes dejarlo así si lo necesitas

    @Column(nullable = false)
    private int age;

    @Column(nullable = true, unique = true)
    private String email;  // ✅ en minúscula por convención

    @Column(nullable = false, unique = true)
    private int phoneNumber;

    @Column(nullable = false)
    private String date;

    @Enumerated(EnumType.STRING) // ✅ mejor guardar enums como texto legible
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true)
    private String gender;

    @Column(nullable = true)
    private String emergencyContactName;

    @Column(nullable = true)
    private Long emergencyContactNumber;

    @Column(nullable = true)
    private String relationshipPatient;

    // 🔹 Constructor vacío (obligatorio para JPA)
    public UserEntity() {
    }

    // 🔹 Constructor sin ID (la BD lo genera automáticamente)
    public UserEntity(Long document, String name, String user, String password, int age,
                      String email, int phoneNumber, String date, Role role,
                      String address, String gender, String emergencyContactName,
                      Long emergencyContactNumber, String relationshipPatient) {
        this.document = document;
        this.name = name;
        this.user = user;
        this.password = password;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.role = role;
        this.address = address;
        this.gender = gender;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.relationshipPatient = relationshipPatient;
    }

    public Long getId()
    {
        
        return id;
        
    }

    public void setId(Long id)
    {
        
        this.id = id;
        
    }

    public Long getDocument()
    {
        
        return document;
        
    }

    public void setDocument(Long document)
    {
        
        this.document = document;
        
    }

    public String getName()
    {
        
        return name;
        
    }

    public String getUser()
    {
        
        return user;
        
    }

    public void setUser(String user)
    {
        
        this.user = user;
        
    }

    public String getPassword()
    {
        
        return password;
        
    }

    public void setPassword(String password)
    {
        
        this.password = password;
        
    }

    public void setName(String name)
    {
        
        this.name = name;
        
    }

    public int getAge()
    {
        
        return age;
        
    }

    public void setAge(int age)
    {
        
        this.age = age;
        
    }

    public String getEmail()
    {
        
        return email;
        
    }

    public void setEmail(String Email)
    {
        
        this.email = Email;
        
    }

    public int getPhoneNumber()
    {
        
        return phoneNumber;
        
    }

    public void setPhoneNumber(int phoneNumber)
    {
        
        this.phoneNumber = phoneNumber;
        
    }

    public String getDate()
    {
        
        return date;
        
    }

    public void setDate(String date)
    {
        
        this.date = date;
        
    }

    public Role getRole()
    {
        
        return role;
        
    }

    public void setRole(Role role)
    {
        
        this.role = role;
        
    }

    public String getAddress()
    {
        
        return address;
        
    }

    public void setAddress(String address)
    {
        
        this.address = address;
        
    }

    public String getGender()
    {
        
        return gender;
        
    }

    public void setGender(String gender)
    {
        
        this.gender = gender;
        
    }

    public String getEmergencyContactName()
    {
        
        return emergencyContactName;
        
    }

    public void setEmergencyContactName(String emergencyContactName)
    {
        
        this.emergencyContactName = emergencyContactName;
        
    }

    public Long getEmergencyContactNumber()
    {
        
        return emergencyContactNumber;
        
    }

    public void setEmergencyContactNumber(Long emergencyContactNumber)
    {
    
        this.emergencyContactNumber = emergencyContactNumber;
        
    }

    public String getRelationshipPatient()
    {
        
        return relationshipPatient;
        
    }

    public void setRelationshipPatient(String relationshipPatient)
    {
        
        this.relationshipPatient = relationshipPatient;
        
    }
    
}
