package app.infrastructure.persistence.mapper;

import app.domain.model.Patient;
import app.infrastructure.persistence.entities.PatientEntity;

public class PatientMapper {

    // ===== Domain → Entity =====
    public static PatientEntity toEntity(Patient patient) {
        if (patient == null) {
            return null;
        }

        return new PatientEntity(
                null, // ID autogenerado por la BD
                patient.getNameComplete(),
                patient.getDocument(),
                patient.getDate(), // 🔹 String, igual que en tu dominio
                patient.getGender(),
                patient.getAddress(),
                patient.getPhoneNumber() != null ? String.valueOf(patient.getPhoneNumber()) : null,
                patient.getEmail(),
                patient.getEmergencyContactName(),
                String.valueOf(patient.getEmergencyContactNumber()),
                patient.getRelationshipPatient()
        );
    }

    // ===== Entity → Domain =====
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
        patient.setDate(entity.getBirthDate());
        patient.setGender(entity.getGender());
        patient.setAddress(entity.getAddress());

        // Conversión de teléfono
        if (entity.getPhoneNumber() != null && !entity.getPhoneNumber().isEmpty()) {
            try {
                patient.setPhoneNumber(Long.parseLong(entity.getPhoneNumber()));
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Número telefónico inválido: " + entity.getPhoneNumber());
            }
        }

        patient.setEmail(entity.getEmail());
        patient.setEmergencyContactName(entity.getEmergencyContactName());

        // Conversión de contacto de emergencia
        if (entity.getEmergencyContactNumber() != null && !entity.getEmergencyContactNumber().isEmpty()) {
            try {
                patient.setEmergencyContactNumber(Long.parseLong(entity.getEmergencyContactNumber()));
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Número de contacto inválido: " + entity.getEmergencyContactNumber());
            }
        }

        patient.setRelationshipPatient(entity.getRelationshipPatient());
        return patient;
    }
}
