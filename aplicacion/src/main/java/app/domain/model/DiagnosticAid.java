package app.domain.model;

public class DiagnosticAid
{
    
    private String orderNumber;
    private String diagnosticId;
    private String quantity;
    private String specialistId;
    private String item;

    public String getOrderNumber()
    {
        
        return orderNumber;
        
    }

    public void setOrderNumber(String orderNumber)
    {
        
        this.orderNumber = orderNumber;
        
    }

    public String getDiagnosticId()
    {
        
        return diagnosticId;
        
    }

    public void setDiagnosticId(String diagnosticId)
    {
        
        this.diagnosticId = diagnosticId;
        
    }

    public String getQuantity()
    {
        
        return quantity;
        
    }

    public void setQuantity(String quantity)
    {
        
        this.quantity = quantity;
        
    }

    public String getSpecialistId()
    {
        
        return specialistId;
        
    }

    public void setSpecialistId(String specialistId)
    {
        
        this.specialistId = specialistId;
        
    }

    public String getItem()
    {
        
        return item;
        
    }

    public void setItem(String item)
    {
        
        this.item = item;
        
    }
    
}
