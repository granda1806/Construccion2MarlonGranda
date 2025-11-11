
package app.domain.ports;

import app.domain.model.ClinicalHistoryRecord;

public interface DoctorPortIn {
    // métodos que la capa de entrada (DoctorMenu / Controller) invocará
    void registerClinicalHistory(ClinicalHistoryRecord record);
    void updateClinicalHistory(ClinicalHistoryRecord record);
    ClinicalHistoryRecord[] findHistoriesByPatient(Long patientDocument);
    ClinicalHistoryRecord findHistoryById(String id);
}
