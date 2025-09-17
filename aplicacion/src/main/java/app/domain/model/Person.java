package app.domain.model;

import app.domain.model.enums.Role;

public class Person
{
    private long id;
    private String name;
    private long document;
    private int age;
    private String Email;
    private int phoneNumber;
    private String date;
    private Role role;
    private String addres;
    
    private String gender;
    private String emergencyContactName;
    private long emergencyContactNumber;
    private String relationshipPatient;

    public long getId()
    {
        
        return id;
        
    }

    public void setId(long id)
    {
        
        this.id = id;
        
    }

    public String getName()
    {
        
        return name;
        
    }

    public void setName(String name)
    {
        
        this.name = name;
        
    }

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

    public long getEmergencyContactNumber()
    {
        
        return emergencyContactNumber;
        
    }

    public void setEmergencyContactNumber(long emergencyContactNumber)
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

    public int getAge()
    {
        
        return age;
        
    }

    public void setAge(int age)
    {
        
        this.age = age;
        
    }

}
