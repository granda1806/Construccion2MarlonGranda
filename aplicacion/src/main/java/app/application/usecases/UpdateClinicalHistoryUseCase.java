package app.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Scanner;

import app.domain.model.ClinicalHistoryRecord;
import app.infrastructure.persistence.repository.ClinicalHistoryRepository;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.mapper.DoctorMapper;

@Service
public class UpdateClinicalHistoryUseCase {

    private final Scanner reader = new Scanner(System.in);
    private final ClinicalHistoryRepository clinicalHistoryRepository;

    @Autowired
    public UpdateClinicalHistoryUseCase(ClinicalHistoryRepository clinicalHistoryRepository) {
        this.clinicalHistoryRepository = clinicalHistoryRepository;
    }

    public void execute() {
        try {
            System.out.println("\n=== Actualización de historia clínica ===");
            System.out.print("Ingrese la cédula del paciente: ");
            Long patientDocument = reader.nextLong();

            List<MedicalHistoryEntity> histories = clinicalHistoryRepository.findByPatientDocument(patientDocument);

            if (histories.isEmpty()) {
                System.out.println("⚠️ No se encontraron historias clínicas para ese paciente.");
                return;
            }

            System.out.println("\nHistorias encontradas:");
            for (int i = 0; i < histories.size(); i++) {
                System.out.println(i + ". Fecha: " + histories.get(i).getDate() + " - ID: " + histories.get(i).getId());
            }

            System.out.print("Seleccione el índice a actualizar: ");
            int index = Integer.parseInt(reader.nextLine());
            if (index < 0 || index >= histories.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            MedicalHistoryEntity entity = histories.get(index);
            ClinicalHistoryRecord record = DoctorMapper.toDomain(entity);

            System.out.println("Editando (deje vacío para mantener el valor actual):");

            System.out.print("Motivo [" + record.getReasonForConsultation() + "]: ");
            String reason = reader.nextLine();
            if (!reason.isBlank()) record.setReasonForConsultation(reason);

            System.out.print("Síntomas [" + record.getSymptoms() + "]: ");
            String symptoms = reader.nextLine();
            if (!symptoms.isBlank()) record.setSymptoms(symptoms);

            System.out.print("Diagnóstico [" + record.getDiagnosis() + "]: ");
            String diagnosis = reader.nextLine();
            if (!diagnosis.isBlank()) record.setDiagnosis(diagnosis);

            // Guardar cambios
            MedicalHistoryEntity updated = DoctorMapper.toEntity(record);
            updated.setId(entity.getId()); // mantener ID original
            clinicalHistoryRepository.save(updated);

            System.out.println("✅ Historia clínica actualizada correctamente en MySQL.");
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar la historia clínica: " + e.getMessage());
        }
    }
}
