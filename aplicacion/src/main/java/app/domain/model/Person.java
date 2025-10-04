package app.domain.model;

import app.domain.model.enums.Role;

public class Person
{

    private String typeId;
    private long id;
    private String nameComplete;
    private String lastnameComplete;
    private String gender;
    private String date;
    private int age;
    private Role role;
    private String email;
    private int phoneNumber;
    private String address;
    
    private String emergencyContactName;
    private String genderEmergencyContact;
    private long emergencyContactNumber;
    private String relationshipPatient;


    /*Methods setter*/
    public void setTypeId(String typeId)
    {
        
        this.typeId = typeId;
        
    }
    
    public void setId(long id)
    {
        
        this.id = id;
        
    }
    
    public void setNameComplete(String nameComplete)
    {
        
        this.nameComplete = nameComplete;
        
    }
    
    public void setLastnameComplete(String lastnameComplete)
    {
        
        this.lastnameComplete = lastnameComplete;
        
    }
    
    public void setGender(String gender)
    {
        
        this.gender = gender;
        
    }
    
    public void setDate(String date)
    {
        
        this.date = date;
        
    }
    
    public void setAge(int age)
    {
        
        this.age = age;
        
    }
    
    public void setRole(Role role)
    {
        
        this.role = role;
        
    }
    
    public void setEmail(String email)
    {
        
        this.email = email;
        
    }
    
    public void setPhoneNumber(int phoneNumber)
    {
        
        this.phoneNumber = phoneNumber;
        
    }
    
    public void setAddress(String address)
    {
        
        this.address = address;
        
    }
    
    public void setEmergencyContactName(String emergencyContactName)
    {
        
        this.emergencyContactName = emergencyContactName;
        
    }

    public void setGenderEmergencyContact(String genderEmergencyContact)
    {
        
        this.genderEmergencyContact = genderEmergencyContact;
        
    }

    public void setEmergencyContactNumber(long emergencyContactNumber)
    {
        
        this.emergencyContactNumber = emergencyContactNumber;
        
    }

    public void setRelationshipPatient(String relationshipPatient)
    {
        
        this.relationshipPatient = relationshipPatient;
        
    }
    
    /*Methods getter*/
    public String getTypeId()
    {
    
        return typeId;
    
    }

    public long getId()
    {

        return id;

    }

    public String getNameComplete()
    {

        return nameComplete;

    }

    public String getLastnameComplete()
    {

        return lastnameComplete;

    }

    public String getGender()
    {

        return gender;

    }

    public String getDate()
    {

        return date;

    }

    public int getAge()
    {

        return age;

    }

    public Role getRole()
    {

        return role;

    }

    public String getEmail()
    {

        return email;

    }

    public int getPhoneNumber()
    {

        return phoneNumber;

    }

    public String getAddress()
    {

        return address;

    }

    public String getEmergencyContactName()
    {

        return emergencyContactName;

    }

    public String getGenderEmergencyContact()
    {

        return genderEmergencyContact;

    }

    public long getEmergencyContactNumber()
    {

        return emergencyContactNumber;

    }

    public String getRelationshipPatient()
    {

        return relationshipPatient;

    }

    public Long getDocument() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getAddres() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}