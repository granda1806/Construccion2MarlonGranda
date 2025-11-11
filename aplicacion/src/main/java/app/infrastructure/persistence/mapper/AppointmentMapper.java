package app.infrastructure.persistence.mapper;

import app.domain.model.Appointment;
import app.domain.model.Patient;
import app.domain.model.User;
import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.UserEntity;

public class AppointmentMapper {

    public static AppointmentEntity toEntity(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(appointment.getId());
        entity.setDate(appointment.getDate());

        // User -> UserEntity
        User admin = appointment.getAdmin();
        if (admin != null) {
            UserEntity adminEntity = new UserEntity();
            adminEntity.setId(admin.getId());
            // Si User tiene nameComplete y lastnameComplete, los guardamos por separado
            adminEntity.setNameComplete(admin.getNameComplete());
            adminEntity.setLastnameComplete(admin.getLastnameComplete());
            adminEntity.setDocument(admin.getDocument());
            adminEntity.setAge(admin.getAge());
            adminEntity.setRole(admin.getRole());
            adminEntity.setUserName(admin.getUserName());
            adminEntity.setPassword(admin.getPassword());
            entity.setAdmin(adminEntity);
        }

        // Patient -> PatientEntity (se asume que PatientEntity tiene 'name' como antes)
        Patient patient = appointment.getPatient();
        if (patient != null) {
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setId(patient.getId());
            patientEntity.setName(patient.getNameComplete());
            patientEntity.setDocument(patient.getDocument());
            entity.setPatient(patientEntity);
        }

        return entity;
    }

    public static Appointment toDomain(AppointmentEntity entity) {
        if (entity == null) {
            return null;
        }

        Appointment appointment = new Appointment();
        appointment.setId(entity.getId());
        appointment.setDate(entity.getDate());

        // UserEntity -> User
        UserEntity adminEntity = entity.getAdmin();
        if (adminEntity != null) {
            User admin = new User();
            admin.setId(adminEntity.getId());
            // Si User tiene setNameComplete / setLastnameComplete:
            admin.setNameComplete(adminEntity.getNameComplete());
            admin.setLastnameComplete(adminEntity.getLastnameComplete());
            admin.setDocument(adminEntity.getDocument());
            admin.setAge(adminEntity.getAge());
            admin.setRole(adminEntity.getRole());
            admin.setUserName(adminEntity.getUserName());
            admin.setPassword(adminEntity.getPassword());
            appointment.setAdmin(admin);
        }

        // PatientEntity -> Patient
        PatientEntity patientEntity = entity.getPatient();
        if (patientEntity != null) {
            Patient patient = new Patient();
            patient.setId(patientEntity.getId());
            patient.setNameComplete(patientEntity.getName());
            patient.setDocument(patientEntity.getDocument());
            appointment.setPatient(patient);
        }

        return appointment;
    }
}
