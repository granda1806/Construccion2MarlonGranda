package app.domain.ports;

import app.domain.model.ClinicalHistoryRecord;
import java.util.List;

public interface DoctorPortOut {
    void saveClinicalHistory(ClinicalHistoryRecord record);
    void updateClinicalHistory(ClinicalHistoryRecord record);
    List<ClinicalHistoryRecord> findByPatient(Long patientDocument);
    ClinicalHistoryRecord findById(String id);
}
