
package app.domain.model;

public class Patient extends Person{
  
    
    private String emergencyContactName;
    private String genderEmergencyContact;
    private long emergencyContactNumber;
    private String relationshipPatient;
    

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getGenderEmergencyContact() {
        return genderEmergencyContact;
    }

    public void setGenderEmergencyContact(String genderEmergencyContact) {
        this.genderEmergencyContact = genderEmergencyContact;
    }

    public long getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(long emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getRelationshipPatient() {
        return relationshipPatient;
    }

    public void setRelationshipPatient(String relationshipPatient) {
        this.relationshipPatient = relationshipPatient;
    }  
}
