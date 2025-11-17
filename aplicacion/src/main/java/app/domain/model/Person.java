
package app.domain.model;

import app.domain.model.enums.Role;

public class Person {

    private String typeId;
    private Long id;
    public String nameComplete;
    public Long document;
    private String lastnameComplete;
    private String gender;
    private int age;
    private Role role;
    private String email;
    private Long phoneNumber;
    private String address;

    /* Methods setter */
    public void setTypeId(String typeId) {

        this.typeId = typeId;

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

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public void setLastnameComplete(String lastnameComplete) {

        this.lastnameComplete = lastnameComplete;

    }

    public void setGender(String gender) {

        this.gender = gender;

    }

    public void setAge(int age) {

        this.age = age;

    }

    public void setRole(Role role) {

        this.role = role;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public void setPhoneNumber(Long phoneNumber) {

        this.phoneNumber = phoneNumber;

    }

    public void setAddress(String address) {

        this.address = address;

    }

    /* Methods getter */
    public String getTypeId() {

        return typeId;

    }

    public Long getId() {

        return id;

    }

    public String getLastnameComplete() {

        return lastnameComplete;

    }

    public String getGender() {

        return gender;

    }

    public int getAge() {

        return age;

    }

    public Role getRole() {

        return role;

    }

    public String getEmail() {

        return email;

    }

    public Long getPhoneNumber() {

        return phoneNumber;

    }

    public String getAddress() {

        return address;

    }

}