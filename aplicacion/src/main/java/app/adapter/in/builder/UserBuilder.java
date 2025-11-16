
package app.adapter.in.builder;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Patient;
import app.domain.model.enums.Role;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;

@Component
public class UserBuilder {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordEncoder passwordEncoder; // 🔹 Inyección de BCrypt

    /**
     * Genera una contraseña segura
     */
    private String generateSecurePassword(int length) {
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

    /**
     * Construye un usuario de tipo HResources
     */
    public User buildHResources(String nameComplete, String lastnameComplete, Long document, int age) {
        User user = new User(nameComplete, lastnameComplete);

        user.setDocument(document);
        user.setAge(age);
        user.setRole(Role.HRESOURCES);

        // 🔹 Generar una contraseña segura antes de codificarla
        String generatedPassword = generateSecurePassword(10);
        user.setPassword(generatedPassword);
        
        // 🔹 Codificar la contraseña antes de persistir
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return user;
    }

    /**
     * Construye un usuario de tipo Paciente
     */
    public Patient buildAdmin(String nameComplete, String lastnameComplete, String document,
                           int age, String date, String gender, String address,
                           String contactName, String relationship,
                           String contactNumber) throws Exception {

        Patient user = new Patient();
        user.setNameComplete(userValidator.nameValidator(nameComplete));
        user.setLastnameComplete(userValidator.nameValidator(lastnameComplete));
        user.setDocument(userValidator.documentValidator(document));
        user.setAge(userValidator.ageValidator(age));
        user.setDate(userValidator.dateValidator(date));
        user.setGender(userValidator.genderValidator(gender));
        user.setAddress(userValidator.addresValidator(address));
        
        user.setEmergencyContactName(userValidator.contactNameValidator(contactName));
        user.setRelationshipPatient(userValidator.relationshipValidator(relationship));
        user.setEmergencyContactNumber(userValidator.contactNumberValidator(contactNumber));

        return user;
    }
}
