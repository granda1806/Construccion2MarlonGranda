
package app.domain.model;

import app.domain.model.enums.Role;
import java.sql.Date;

public class Person {
    private long id;
    private String name;
    private long document;
    private String Email;
    private int phoneNumber;
    private Date date;
    private String addres;
    private Role role;
    private User loginDetails;
    private Patient patientData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDocument() {
        return document;
    }

    public void setDocument(long document) {
        this.document = document;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(User loginDetails) {
        this.loginDetails = loginDetails;
    }

    public Patient getPatientData() {
        return patientData;
    }

    public void setPatientData(Patient patientData) {
        this.patientData = patientData;
    }
     
}
