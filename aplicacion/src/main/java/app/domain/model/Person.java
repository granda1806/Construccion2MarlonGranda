package app.domain.model;

import app.domain.model.enums.Role;

public class Person
{

    private String typeId;
    private long id;
<<<<<<< HEAD
    private String name;
    private long document;
    private int age;
    private String Email;
    private Long phoneNumber;
    private String date;
    private Role role;
    private String addres;
    
=======
    private String nameComplete;
    private String lastnameComplete;
>>>>>>> 9542caac1b9325a98c02d414474bdf3298dc734a
    private String gender;
    private String date;
    private int age;
    private Role role;
    private String email;
    private int phoneNumber;
    private String address;
    
    private String emergencyContactName;
<<<<<<< HEAD
    private Long emergencyContactNumber;
=======
    private String genderEmergencyContact;
    private long emergencyContactNumber;
>>>>>>> 9542caac1b9325a98c02d414474bdf3298dc734a
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
<<<<<<< HEAD

    public long getDocument()
    {
        
        return document;
        
    }

    public void setDocument(long document)
    {
        
        this.document = document;
        
    }

    public String getEmail()
    {
        
        return Email;
        
    }

    public void setEmail(String Email)
    {
        
        this.Email = Email;
        
    }

    public Long getPhoneNumber()
    {
        
        return phoneNumber;
        
    }

    public void setPhoneNumber(Long phoneNumber)
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

    public String getAddres()
    {
        
        return addres;
        
    }

    public void setAddres(String addres)
    {
        
        this.addres = addres;
        
    }

    public String getGender()
    {
        
        return gender;
        
    }

=======
    
>>>>>>> 9542caac1b9325a98c02d414474bdf3298dc734a
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

<<<<<<< HEAD
    public Long getEmergencyContactNumber()
=======
    public void setGenderEmergencyContact(String genderEmergencyContact)
>>>>>>> 9542caac1b9325a98c02d414474bdf3298dc734a
    {
        
        this.genderEmergencyContact = genderEmergencyContact;
        
    }

    public void setEmergencyContactNumber(Long emergencyContactNumber)
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
<<<<<<< HEAD
}
=======

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
>>>>>>> 9542caac1b9325a98c02d414474bdf3298dc734a
