package app.infrastructure.persistence.entities;

import app.domain.model.User;
import app.domain.model.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long document;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String user;
    
    @Column(nullable = false, unique = true)
    private String password;
    
    @Column(nullable = false)
    private int age;
    
    @Column(nullable = true, unique = true)
    private String Email;
    
    @Column(nullable = false, unique = true)
    private int phoneNumber;
    
    @Column(nullable = false)
    private String date;
    
    @Column(nullable = false)
    private Role role;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = true)
    private User loginDetails;
    
    @Column(nullable = true)    
    private String gender;
    
    @Column(nullable = true)
    private String emergencyContactName;
    
    @Column(nullable = true)
    private Long emergencyContactNumber;
    
    @Column(nullable = true)
    private String relationshipPatient;
    

    public UserEntity() {}

    public UserEntity(Long id, Long document, String name, String user, String password, int age,
                    String Email, int phoneNumber, String date, Role role,
                    String address, User loginDetails, String gender, String emergencyContactName,
                    Long emergencyContactNumber, String relationshipPatient) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.user = user;
        this.password = password;
        this.age = age;
        this.Email = Email;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.role = role;
        this.address = address;
        this.loginDetails = loginDetails;
        this.gender = gender;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.relationshipPatient = relationshipPatient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(User loginDetails) {
        this.loginDetails = loginDetails;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public Long getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(Long emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getRelationshipPatient() {
        return relationshipPatient;
    }

    public void setRelationshipPatient(String relationshipPatient) {
        this.relationshipPatient = relationshipPatient;
    }

    
    
    
}
