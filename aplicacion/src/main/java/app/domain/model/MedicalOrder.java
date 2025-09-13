package app.domain.model;
        
import java.util.ArrayList;
import java.util.List;

public class MedicalOrder {
    private String orderNumber;
    private List<Prescription> prescriptions = new ArrayList<>();
    private List<Procedure> procedures = new ArrayList<>();
    private List<DiagnosticAid> diagnosticAids = new ArrayList<>();
    
    public String getOrderNumber() {return orderNumber;}
    public void setOrderNumber(String orderNumber) {this.orderNumber = orderNumber;}
    
    public List<Prescription> getPrescriptions() {return prescriptions;}
    public void addPrescription(Prescription prescription) {this.prescriptions.add(prescription);}
    
    public List<Procedure> getProcedures() {return procedures;}
    public void addProcedure(Procedure procedure) {this.procedures.add(procedure);}
    
    public List<DiagnosticAid> getDiagnosticAids() {return diagnosticAids;}
    public void addDiagnosticAid(DiagnosticAid diagnosticAid) {this.diagnosticAids.add(diagnosticAid);}
    
    public String toString() {
        return "\n ORDEN MEDICA" +
                "\nNumero de orden: " + orderNumber +
                "\nPrescriocones: " + prescriptions.size() +
                "\nProcedimientos: " + procedures.size() +
                "\ndiagnosticAids: " + diagnosticAids.size();
    
    }
}

