package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class MedicalOrder {

    private String orderNumber;
    private String patientId;
    private String doctorId;
    private String date;

    private final List<Prescription> prescriptions = new ArrayList<>();
    private final List<Procedure> procedures = new ArrayList<>();
    private final List<DiagnosticAid> diagnosticAids = new ArrayList<>();

    // ====== Getters y Setters ======

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        if (orderNumber == null) {
            this.orderNumber = null;
            return;
        }
        if (!orderNumber.matches("^\\d{1,6}$")) {
            throw new IllegalArgumentException("El número de orden debe contener sólo dígitos y máximo 6 caracteres.");
        }
        this.orderNumber = orderNumber;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        if (prescription != null) {
            this.prescriptions.add(prescription);
        }
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void addProcedure(Procedure procedure) {
        if (procedure != null) {
            this.procedures.add(procedure);
        }
    }

    public List<DiagnosticAid> getDiagnosticAids() {
        return diagnosticAids;
    }

    public void addDiagnosticAid(DiagnosticAid diagnosticAid) {
        if (diagnosticAid != null) {
            this.diagnosticAids.add(diagnosticAid);
        }
    }

    // ====================== MÉTODO PARA VERIFICAR ÍTEM DUPLICADO
    // ======================
    public boolean containsItem(String itemNumber) {
        if (itemNumber == null || itemNumber.isEmpty()) {
            return false;
        }

        // Buscar en prescripciones
        for (Prescription p : prescriptions) {
            if (p != null && itemNumber.equals(p.getItem())) {
                return true;
            }
        }

        // Buscar en procedimientos
        for (Procedure pr : procedures) {
            if (pr != null && itemNumber.equals(pr.getItem())) {
                return true;
            }
        }

        // Buscar en ayudas diagnósticas
        for (DiagnosticAid d : diagnosticAids) {
            if (d != null && itemNumber.equals(d.getItem())) {
                return true;
            }
        }

        return false;
    }

    // ====================== REPRESENTACIÓN DE TEXTO ======================
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== ORDEN MÉDICA =====")
                .append("\nNúmero de orden: ").append(orderNumber != null ? orderNumber : "Sin asignar")
                .append("\nCédula paciente: ").append(patientId != null ? patientId : "No registrada")
                .append("\nCédula médico: ").append(doctorId != null ? doctorId : "No registrada")
                .append("\nFecha: ").append(date != null ? date : "No registrada");

        // --- Prescripciones ---
        sb.append("\n\n--- PRESCRIPCIONES ---");
        if (prescriptions.isEmpty()) {
            sb.append("\nNo hay prescripciones registradas.");
        } else {
            for (Prescription p : prescriptions) {
                sb.append("\n• Medicamento ID: ").append(p.getMedicineId())
                        .append(" | Dosis: ").append(p.getDose())
                        .append(" | Duración: ").append(p.getDuration())
                        .append(" | Ítem: ").append(p.getItem());
            }
        }

        // --- Procedimientos ---
        sb.append("\n\n--- PROCEDIMIENTOS ---");
        if (procedures.isEmpty()) {
            sb.append("\nNo hay procedimientos registrados.");
        } else {
            for (Procedure pr : procedures) {
                sb.append("\n• Procedimiento ID: ").append(pr.getProcedureId())
                        .append(" | Cantidad: ").append(pr.getQuantity())
                        .append(" | Frecuencia: ").append(pr.getFrequency())
                        .append(" | Costo: ").append(pr.getCost())
                        .append(" | Especialista: ").append(
                                pr.getSpecialistId() != null ? pr.getSpecialistId() : "No requerido")
                        .append(" | Ítem: ").append(pr.getItem());
            }
        }

        // --- Ayudas Diagnósticas ---
        sb.append("\n\n--- AYUDAS DIAGNÓSTICAS ---");
        if (diagnosticAids.isEmpty()) {
            sb.append("\nNo hay ayudas diagnósticas registradas.");
        } else {
            for (DiagnosticAid a : diagnosticAids) {
                sb.append("\n• Diagnóstico ID: ").append(a.getDiagnosticId())
                        .append(" | Cantidad: ").append(a.getQuantity())
                        .append(" | Especialista: ").append(
                                a.getSpecialistId() != null ? a.getSpecialistId() : "No requerido")
                        .append(" | Ítem: ").append(a.getItem());
            }
        }

        return sb.toString();
    }
}
