package app.infrastructure.persistence.mapper;

import app.domain.model.Person;
import app.infrastructure.persistence.entities.PatientEntity;

public class PatientMapper {

    public static PatientEntity toEntity(Person patient) {
        if (patient == null) {
            return null;
        }
        return new PatientEntity(
                null,
                patient.getNameComplete(),
                patient.getDocument(),
                patient.getDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getEmergencyContactName(),
                patient.getEmergencyContactNumber(),
                patient.getRelationshipPatient()
        );
    }
    
    // Entity -> Domain
    public static Person toDomain(PatientEntity entity) {
        if (entity == null) return null;
        
        Person user = new Person();
        Person person = new Person();
        person.setId(entity.getId());
        person.setNameComplete(entity.getName());
        person.setDocument(entity.getDocument());
        person.setDate(entity.getDate());
        person.setGender(entity.getGender());
        person.setAddress(entity.getAddress());
        person.setPhoneNumber(entity.getPhoneNumber());
        person.setEmail(entity.getEmail());
        person.setEmergencyContactName(entity.getEmergencyContactName());
        person.setEmergencyContactNumber(entity.getEmergencyContactNumber());
        person.setRelationshipPatient(entity.getRelationshipPatient());
        return person;       
    }
}
