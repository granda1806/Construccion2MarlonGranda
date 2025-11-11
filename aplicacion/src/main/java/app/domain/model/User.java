package app.domain.model;

import jakarta.persistence.Column;
import java.security.SecureRandom;

public class User extends Person {

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String nameComplete;

    @Column(nullable = false)
    private String lastnameComplete;

    private String password;

    public User() {
        // Constructor vacío requerido por frameworks (Hibernate, etc.)
    }

    // ==================== Constructores ====================

    public User(String nameComplete, String lastnameComplete) {
        this.nameComplete = nameComplete;
        this.lastnameComplete = lastnameComplete;
        this.userName = generateUserName(nameComplete, lastnameComplete);
        this.password = generatePassword(10);
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

    private String generatePassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("length must be >= 4");
        }

        final String U = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String L = "abcdefghijklmnopqrstuvwxyz";
        final String D = "0123456789";
        final String S = "+-*/.,_";
        final String ALL = U + L + D + S;

        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        sb.append(U.charAt(rnd.nextInt(U.length())));
        sb.append(L.charAt(rnd.nextInt(L.length())));
        sb.append(D.charAt(rnd.nextInt(D.length())));
        sb.append(S.charAt(rnd.nextInt(S.length())));

        for (int i = 4; i < length; i++) {
            sb.append(ALL.charAt(rnd.nextInt(ALL.length())));
        }

        // Mezcla aleatoria final
        char[] arr = sb.toString().toCharArray();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        return new String(arr);
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
