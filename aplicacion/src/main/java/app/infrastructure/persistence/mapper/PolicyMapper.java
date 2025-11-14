package app.infrastructure.persistence.mapper;

import app.domain.model.Patient;
import app.domain.model.Policy;
import app.domain.model.User;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.PolicyEntity;
import app.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {

    // Domain → Entity
    public static PolicyEntity toEntity(Policy policy) {
        if (policy == null) {
            return null;
        }

        PolicyEntity entity = new PolicyEntity();
        entity.setId(policy.getId());
        entity.setPolicyTerminationDate(policy.getPolicyTerminationDate());
        entity.setPolicyNumber(policy.getPolicyNumber());
        entity.setPolicyStatus(policy.isPolicyStatus());

        // Si el dominio usa String para policyName, conviértelo al enum

        // Admin
        User admin = policy.getAdmin();
        if (admin != null) {
            UserEntity adminEntity = new UserEntity();
            adminEntity.setId(admin.getId());
            adminEntity.setNameComplete(admin.getNameComplete());
            adminEntity.setDocument(admin.getDocument());
            adminEntity.setAge(admin.getAge());
            adminEntity.setRole(admin.getRole());
            adminEntity.setUserName(admin.getUserName());
            adminEntity.setPassword(admin.getPassword());
            entity.setAdmin(adminEntity);
        }

        // Patient
        Patient patient = policy.getPatient();
        if (patient != null) {
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setId(patient.getId());
            patientEntity.setName(patient.getNameComplete());
            patientEntity.setDocument(patient.getDocument());
            entity.setPatient(patientEntity);
        }

        return entity;
    }

    // Entity → Domain
    public static Policy toDomain(PolicyEntity entity) {
        if (entity == null) {
            return null;
        }

        Policy policy = new Policy();
        policy.setId(entity.getId());
        policy.setPolicyTerminationDate(entity.getPolicyTerminationDate());
        policy.setPolicyNumber(entity.getPolicyNumber());
        policy.setPolicyStatus(entity.isPolicyStatus());
        policy.setPolicyName(entity.getNamePolicy());

        // Admin
        UserEntity adminEntity = entity.getAdmin();
        if (adminEntity != null) {
            User admin = new User();
            admin.setId(adminEntity.getId());
            admin.setName(adminEntity.getNameComplete());
            admin.setDocument(adminEntity.getDocument());
            admin.setAge(adminEntity.getAge());
            admin.setRole(adminEntity.getRole());
            admin.setUserName(adminEntity.getUserName());
            admin.setPassword(adminEntity.getPassword());
            policy.setAdmin(admin);
        }

        // Patient
        PatientEntity patientEntity = entity.getPatient();
        if (patientEntity != null) {
            Patient patient = new Patient();
            patient.setId(patientEntity.getId());
            patient.setNameComplete(patientEntity.getName());
            patient.setDocument(patientEntity.getDocument());
            policy.setPatient(patient);
        }

        return policy;
    }
}
