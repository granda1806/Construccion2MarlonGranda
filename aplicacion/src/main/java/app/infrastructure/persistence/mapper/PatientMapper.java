package app.infrastructure.persistence.mapper;

import app.domain.model.Patient;
import app.infrastructure.persistence.entities.PatientEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientMapper {

    private static final Logger logger = LoggerFactory.getLogger(PatientMapper.class);

    /**
     * Convierte de modelo de dominio (Patient) a entidad JPA (PatientEntity)
     */
    public static PatientEntity toEntity(Patient patient) {
        if (patient == null) {
            return null;
        }

        PatientEntity entity = new PatientEntity();

        // Mantener ID si existe (para updates)
        if (patient.getId() != null && patient.getId() > 0) {
            entity.setId(patient.getId());
        }

        entity.setName(patient.getNameComplete());
        entity.setDocument(patient.getDocument());
        entity.setGender(patient.getGender());
        entity.setAddress(patient.getAddress());

        entity.setAge(patient.getAge());

        // Conversión segura de teléfono
        if (patient.getPhoneNumber() != null && patient.getPhoneNumber() > 0) {
            entity.setPhoneNumber(String.valueOf(patient.getPhoneNumber()));
        }

        entity.setEmail(patient.getEmail());
        entity.setEmergencyContactName(patient.getEmergencyContactName());

        // Conversión segura de contacto de emergencia
        if (patient.getEmergencyContactNumber() > 0) {
            entity.setEmergencyContactNumber(String.valueOf(patient.getEmergencyContactNumber()));
        }

        entity.setRelationshipPatient(patient.getRelationshipPatient());

        return entity;
    }

    /**
     * Convierte de entidad JPA (PatientEntity) a modelo de dominio (Patient)
     */
    public static Patient toDomain(PatientEntity entity) {
        if (entity == null) {
            return null;
        }

        Patient patient = new Patient();

        if (entity.getId() != null) {
            patient.setId(entity.getId());
        }

        patient.setNameComplete(entity.getName());
        patient.setDocument(entity.getDocument());
        patient.setGender(entity.getGender());
        patient.setAddress(entity.getAddress());

        patient.setAge(entity.getAge());

        // Conversión segura de teléfono
        if (entity.getPhoneNumber() != null && !entity.getPhoneNumber().trim().isEmpty()) {
            try {
                patient.setPhoneNumber(Long.parseLong(entity.getPhoneNumber()));
            } catch (NumberFormatException e) {
                logger.warn("Número telefónico inválido para paciente {}: {}", entity.getId(), entity.getPhoneNumber());
            }
        }

        patient.setEmail(entity.getEmail());
        patient.setEmergencyContactName(entity.getEmergencyContactName());

        // Conversión segura de contacto de emergencia
        if (entity.getEmergencyContactNumber() != null && !entity.getEmergencyContactNumber().trim().isEmpty()) {
            try {
                patient.setEmergencyContactNumber(Long.parseLong(entity.getEmergencyContactNumber()));
            } catch (NumberFormatException e) {
                logger.warn("Número de contacto inválido para paciente {}: {}", entity.getId(),
                        entity.getEmergencyContactNumber());
            }
        }

        patient.setRelationshipPatient(entity.getRelationshipPatient());
        return patient;
    }
}
