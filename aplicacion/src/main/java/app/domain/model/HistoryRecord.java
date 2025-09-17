package app.domain.model;

public class HistoryRecord
{
    
    private String date;
    private String doctorId;
    private String reason;
    private String symptoms;
    private String diagnosis;

    public String getDate()
    {
        
        return date;
        
    }

    public void setDate(String date)
    {
        
        this.date = date;
        
    }

    public String getDoctorId()
    {
        
        return doctorId;
        
    }

    public void setDoctorId(String doctorId)
    {
        
        this.doctorId = doctorId;
        
    }

    public String getReason()
    {
        
        return reason;
        
    }

    public void setReason(String reason)
    {
        
        this.reason = reason;
        
    }

    public String getSymptoms()
    {
        
        return symptoms;
        
    }

    public void setSymptoms(String symptoms)
    {
        
        this.symptoms = symptoms;
        
    }

    public String getDiagnosis()
    {
    
        return diagnosis;
        
    }

    public void setDiagnosis(String diagnosis)
    {
        
        this.diagnosis = diagnosis;
        
    }
    
}