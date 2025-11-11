package app.domain.model;


public class ClinicalOrder {
    private long id;
    private User documentPatient;
    private User documentDoctor;
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getDocumentPatient() {
        return documentPatient;
    }

    public void setDocumentPatient(User documentPatient) {
        this.documentPatient = documentPatient;
    }

    public User getDocumentDoctor() {
        return documentDoctor;
    }

    public void setDocumentDoctor(User documentDoctor) {
        this.documentDoctor = documentDoctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
