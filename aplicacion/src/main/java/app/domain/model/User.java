package app.domain.model;

import jakarta.persistence.Column;

public class User extends Person
{

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String nameComplete;

    @Column(nullable = false)  // Agregado para Hibernate
    private String lastnameComplete;

    private String password;

    public User()
    {
        // Constructor vacío requerido por frameworks (Hibernate, etc.)
    }

    // ==================== Constructores ====================

    public User(String nameComplete, String lastnameComplete) {
        this.nameComplete = nameComplete;         // 🔹 Se asigna el nombre completo
        this.lastnameComplete = lastnameComplete; // 🔹 Se asigna el apellido completo
        this.userName = generateUserName(nameComplete, lastnameComplete);
    }

    // ==================== Métodos de generación ====================

    private String generateUserName(String nameComplete, String lastnameComplete) {
        String firstNameOnly = (nameComplete != null && !nameComplete.isBlank())
                ? nameComplete.trim().split("\\s+")[0]
                : "user";

        String firstLastnameOnly = (lastnameComplete != null && !lastnameComplete.isBlank())
                ? lastnameComplete.trim().split("\\s+")[0]
                : "surname";

        return (firstNameOnly + "." + firstLastnameOnly).toLowerCase();
    }

    // ==================== Getters y Setters ====================

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameComplete() {
        return nameComplete;
    }

    public void setNameComplete(String nameComplete) {
        this.nameComplete = nameComplete;
    }

    public String getLastnameComplete() {
        return lastnameComplete;
    }

    public void setLastnameComplete(String lastnameComplete) {
        this.lastnameComplete = lastnameComplete;
    }

    public Long getDocument() {
        return this.document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public void setName(String name) {
        this.nameComplete = name;
    }
}
