package app.domain.model;

public class Procedure
{
    
    private String orderNumber;
    private String procedureId;
    private String quantity;
    private String frequency;
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

    public String getProcedureId()
    {
        
        return procedureId;
        
    }

    public void setProcedureId(String procedureId)
    {
        
        this.procedureId = procedureId;
        
    }

    public String getQuantity()
    {
        
        return quantity;
        
    }

    public void setQuantity(String quantity)
    {
        
        this.quantity = quantity;
        
    }

    public String getFrequency()
    {
        
        return frequency;
        
    }

    public void setFrequency(String frequency)
    {
        
        this.frequency = frequency;
        
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
