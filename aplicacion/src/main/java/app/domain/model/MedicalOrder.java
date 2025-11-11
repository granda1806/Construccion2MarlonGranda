package app.domain.model;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class MedicalOrder
{
    
    private String orderNumber;
    private String patientId;
    private String doctorId;
    private String date;
    
    private List<Prescription> prescriptions = new ArrayList<>();
    private List<Procedure> procedures = new ArrayList<>();
    private List<DiagnosticAid> diagnosticAids = new ArrayList<>();

    // Getters y setters
    public String getPatientId()
    {
        
        return patientId;
        
    }

    public void setPatientId(String patientId)
    {
        
        this.patientId = patientId;
        
    }

    public String getDoctorId()
    {
        
        return doctorId;
        
    }

    public void setDoctorId(String doctorId)
    {
        
        this.doctorId = doctorId;
        
    }

    public String getDate()
    {
        
        return date;
        
    }

    public void setDate(String date)
    {
        
        this.date = date;
        
    }
    
    public String getOrderNumber()
    {
        
        return orderNumber;
        
    }

    public void setOrderNumber(String orderNumber)
    {
        
        this.orderNumber = orderNumber;
        
    }

    public List<Prescription> getPrescriptions()
    {
        
        return prescriptions;
        
    }

    public void addPrescription(Prescription prescription)
    {
        
        this.prescriptions.add(prescription);
        
    }

    public List<Procedure> getProcedures()
    {
        
        return procedures;
        
    }

    public void addProcedure(Procedure procedure)
    {
        
        this.procedures.add(procedure);
        
    }

    public List<DiagnosticAid> getDiagnosticAids()
    {
        
        return diagnosticAids;
        
    }

    public void addDiagnosticAid(DiagnosticAid diagnosticAid)
    {
        
        this.diagnosticAids.add(diagnosticAid);
        
    }

    // Método de validación: evita ítems duplicados
    public boolean containsItem(String itemNumber)
    {
        
        for (Prescription p : prescriptions)
        {
            
            if (p.getItem().equals(itemNumber))
            return true;
            
        }

        for (Procedure pr : procedures)
        {
            
            if (pr.getItem().equals(itemNumber))
            return true;
            
        }

        for (DiagnosticAid d : diagnosticAids)
        {
            
            if (d.getItem().equals(itemNumber))
            return true;
            
        }

        return false;
        
    }

    @Override
    public String toString()
    {
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nORDEN MEDICA")
          .append("\nNumero de orden: ").append(orderNumber)
          .append("\nCedula paciente: ").append(patientId)
          .append("\nCedula medico: ").append(doctorId)
          .append("\nFecha: ").append(date);

        sb.append("\n\nPRESCRIPCIONES");
        
        if (prescriptions.isEmpty())
        {
            
            sb.append("\nNo hay prescripciones registradas.");
            
        }
        else
        {
            
            for (Prescription p : prescriptions)
            {
                
                sb.append("\nMedicamento ID: ").append(p.getMedicineId())
                  .append(", Dosis: ").append(p.getDose())
                  .append(", Duracion: ").append(p.getDuration())
                  .append(", Item: ").append(p.getItem());
                
=======
public class MedicalOrder {

    private String orderNumber;
    private final List<Prescription> prescriptions = new ArrayList<>();
    private final List<Procedure> procedures = new ArrayList<>();
    private final List<DiagnosticAid> diagnosticAids = new ArrayList<>();

    // ====== Getters y Setters ======
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    // ====================== MÉTODO PARA VERIFICAR ÍTEM DUPLICADO ======================
    public boolean containsItem(String item) {
        if (item == null || item.isEmpty()) {
            return false;
        }

        // Verificar en prescripciones
        for (Prescription p : prescriptions) {
            if (item.equals(p.getItem())) {
                return true;
            }
        }

        // Verificar en procedimientos
        for (Procedure pr : procedures) {
            if (item.equals(pr.getItem())) {
                return true;
            }
        }

        // Verificar en ayudas diagnósticas
        for (DiagnosticAid a : diagnosticAids) {
            if (item.equals(a.getItem())) {
                return true;
            }
        }

        return false;
    }

    // ====== toString() mejorado ======
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== ORDEN MÉDICA =====")
                .append("\nNúmero de orden: ").append(orderNumber != null ? orderNumber : "Sin asignar");

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
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
            }
            
        }

<<<<<<< HEAD
        sb.append("\n\nPROCEDIMIENTOS");
        
        if (procedures.isEmpty())
        {
            
            sb.append("\nNo hay procedimientos registrados.");
            
        }
        else
        {
            
            for (Procedure pr : procedures)
            {
                
                sb.append("\nProcedimiento ID: ").append(pr.getProcedureId())
                  .append(", Cantidad: ").append(pr.getQuantity())
                  .append(", Frecuencia: ").append(pr.getFrequency())
                  .append(", Especialista: ").append(pr.getSpecialistId() != null ? pr.getSpecialistId() : "No requerido")
                  .append(", Item: ").append(pr.getItem());
                
=======
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
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
            }
            
        }

<<<<<<< HEAD
        sb.append("\n\nAYUDAS DIAGNOSTICAS");
        
        if (diagnosticAids.isEmpty())
        {
            
            sb.append("\nNo hay ayudas diagnosticas registradas.");
            
        }
        else
        {
            
            for (DiagnosticAid a : diagnosticAids)
            {
                
                sb.append("\nDiagnostico ID: ").append(a.getDiagnosticId())
                  .append(", Cantidad: ").append(a.getQuantity())
                  .append(", Especialista: ").append(a.getSpecialistId() != null ? a.getSpecialistId() : "No requerido")
                  .append(", Item: ").append(a.getItem());
                
=======
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
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
            }
            
        }

        return sb.toString();
        
    }
}
