package app.domain.model;

import java.util.ArrayList;
import java.util.List;

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
                
            }
            
        }

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
                
            }
            
        }

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
                
            }
            
        }

        return sb.toString();
        
    }
    
}
