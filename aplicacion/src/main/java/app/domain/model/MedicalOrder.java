package app.domain.model;
        
import java.util.ArrayList;
import java.util.List;

public class MedicalOrder
{
    
    private String orderNumber;
    private List<Prescription> prescriptions = new ArrayList<>();
    private List<Procedure> procedures = new ArrayList<>();
    private List<DiagnosticAid> diagnosticAids = new ArrayList<>();
    
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
    
        public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nORDEN MEDICA")
          .append("\nNumero de orden: ").append(orderNumber);

        // Prescripciones
        sb.append("\n\nPRESCRIPCIONES");
        if (prescriptions.isEmpty()) {
            sb.append("\nNo hay prescripciones registradas.");
        } else {
            for (Prescription p : prescriptions) {
                sb.append("\nMedicamento ID: ").append(p.getMedicineId())
                  .append(", Dosis: ").append(p.getDose())
                  .append(", Duracion: ").append(p.getDuration())
                  .append(", Item: ").append(p.getItem());
            }
        }

        // Procedimientos
        sb.append("\n\nPROCEDIMIENTOS");
        if (procedures.isEmpty()) {
            sb.append("\nNo hay procedimientos registrados.");
        } else {
            for (Procedure pr : procedures) {
                sb.append("\nProcedimiento ID: ").append(pr.getProcedureId())
                  .append(", Cantidad: ").append(pr.getQuantity())
                  .append(", Frecuencia: ").append(pr.getFrequency())
                  .append(", Especialista: ").append(pr.getSpecialistId() != null ? pr.getSpecialistId() : "No requerido")
                  .append(", Item: ").append(pr.getItem());
            }
        }

        // Ayudas diagnósticas
        sb.append("\n\nAYUDAS DIAGNOSTICAS");
        if (diagnosticAids.isEmpty()) {
            sb.append("\nNo hay ayudas diagnosticas registradas.");
        } else {
            for (DiagnosticAid a : diagnosticAids) {
                sb.append("\nDiagnostico ID: ").append(a.getDiagnosticId())
                  .append(", Cantidad: ").append(a.getQuantity())
                  .append(", Especialista: ").append(a.getSpecialistId() != null ? a.getSpecialistId() : "No requerido")
                  .append(", Item: ").append(a.getItem());
            }
        }

        return sb.toString();
    }
    
}

