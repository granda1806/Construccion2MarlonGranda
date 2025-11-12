package app.adapter.out;

import app.domain.model.Patient;
import app.domain.ports.UserPortPatient;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientAdapter implements UserPortPatient {

    private final PatientRepository patientRepository;

    public PatientAdapter(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findByDocument(Patient user) throws Exception {
        PatientEntity patientEntity = patientRepository.findByDocument(user.getDocument());
        if (patientEntity == null) {
            throw new RuntimeException("Paciente no encontrado");
        }
        return PatientMapper.toDomain(patientEntity);
    }

    @Override
    public Patient findByName(Patient user) throws Exception {
        // Aquí puedes implementar la búsqueda por nombre si lo necesitas
        return null;
    }

    @Override
    public void save(Patient user) throws Exception {
        patientRepository.save(PatientMapper.toEntity(user));
        System.out.println("Se ha creado el paciente " + user.getNameComplete() + " correctamente");
    }
}

