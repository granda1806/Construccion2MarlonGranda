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
            Long patientDocument = Long.parseLong(reader.nextLine().trim());

            List<MedicalHistoryEntity> histories = clinicalHistoryRepository.findByPatientDocument(patientDocument);

            if (histories == null || histories.isEmpty()) {
                System.out.println("⚠️ No se encontraron historias clínicas para ese paciente.");
                return;
            }

            System.out.println("\nHistorias encontradas:");
            for (int i = 0; i < histories.size(); i++) {
                System.out.println((i + 1) + ". Fecha: " + histories.get(i).getDate() + " - ID: " + histories.get(i).getId());
            }

            System.out.print("Seleccione el número de registro a actualizar: ");
            String indexInput = reader.nextLine().trim();
            if (indexInput.isEmpty() || !indexInput.matches("\\d+")) {
                System.out.println("Índice inválido.");
                return;
            }

            int index = Integer.parseInt(indexInput) - 1;
            if (index < 0 || index >= histories.size()) {
                System.out.println("Índice fuera de rango.");
                return;
            }

            MedicalHistoryEntity entity = histories.get(index);
            ClinicalHistoryRecord record = DoctorMapper.toDomain(entity);

            System.out.println("\nEditando registro (deje vacío para mantener el valor actual):");

            System.out.print("Motivo [" + record.getReasonForConsultation() + "]: ");
            String reason = reader.nextLine().trim();
            if (!reason.isEmpty()) record.setReasonForConsultation(reason);

            System.out.print("Síntomas [" + record.getSymptoms() + "]: ");
            String symptoms = reader.nextLine().trim();
            if (!symptoms.isEmpty()) record.setSymptoms(symptoms);

            System.out.print("Diagnóstico [" + record.getDiagnosis() + "]: ");
            String diagnosis = reader.nextLine().trim();
            if (!diagnosis.isEmpty()) record.setDiagnosis(diagnosis);

            // ✅ Guardar cambios
            MedicalHistoryEntity updated = DoctorMapper.toEntity(record);
            updated.setId(entity.getId()); // mantener el ID original
            updated.setPatientDocument(entity.getPatientDocument()); // mantener referencia paciente

            clinicalHistoryRepository.save(updated);
            System.out.println("✅ Historia clínica actualizada correctamente en MySQL.");

        } catch (NumberFormatException e) {
            System.err.println("❌ Error: la cédula debe ser un número válido.");
        } catch (Exception e) {
            System.err.println("❌ Error inesperado al actualizar la historia clínica: " + e.getMessage());
        }
    }
}
