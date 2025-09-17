package app.domain.model;

import java.sql.Date;

public class Policy
{
    
    private String policyName;
    private int policyNumber;
    private boolean policyStatus;
    private Date policyTerminationDate;

    public String getPolicyName()
    {
        
        return policyName;
        
    }

    public void setPolicyName(String policyName)
    {
        
        this.policyName = policyName;
        
    }

    public int getPolicyNumber()
    {
        
        return policyNumber;
        
    }

    public void setPolicyNumber(int policyNumber)
    {
        
        this.policyNumber = policyNumber;
        
    }

    public boolean isPolicyStatus()
    {
        
        return policyStatus;
        
    }

    public void setPolicyStatus(boolean policyStatus)
    {
        
        this.policyStatus = policyStatus;
        
    }

    public Date getPolicyTerminationDate()
    {
        
        return policyTerminationDate;
        
    }

    public void setPolicyTerminationDate(Date policyTerminationDate)
    {
        
        this.policyTerminationDate = policyTerminationDate;
        
    }
 
}
