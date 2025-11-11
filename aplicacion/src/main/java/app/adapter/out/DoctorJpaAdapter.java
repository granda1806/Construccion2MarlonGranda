package app.adapter.out;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

import app.domain.ports.DoctorPortOut;
import app.domain.model.ClinicalHistoryRecord;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.repository.ClinicalHistoryRepository;
import app.infrastructure.persistence.mapper.DoctorMapper;

@Component
public class DoctorJpaAdapter implements DoctorPortOut {

    private final ClinicalHistoryRepository repository;

    public DoctorJpaAdapter(ClinicalHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveClinicalHistory(ClinicalHistoryRecord record) {
        MedicalHistoryEntity entity = DoctorMapper.toEntity(record);
        repository.save(entity);
        System.out.println("✅ Historia clínica guardada en MySQL.");
    }

    @Override
    public void updateClinicalHistory(ClinicalHistoryRecord record) {
        MedicalHistoryEntity entity = DoctorMapper.toEntity(record);
        repository.save(entity);
        System.out.println("🩺 Historia clínica actualizada.");
    }

    @Override
    public List<ClinicalHistoryRecord> findByPatient(Long patientDocument) {
        return repository.findByPatientDocument(Long.valueOf(patientDocument))
                .stream()
                .map(DoctorMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ClinicalHistoryRecord findById(String id) {
        return repository.findById(Long.parseLong(id))
                .map(DoctorMapper::toDomain)
                .orElse(null);
    }
}
