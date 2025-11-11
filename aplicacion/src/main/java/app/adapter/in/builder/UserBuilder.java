package app.adapter.in.builder;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Patient;
import app.domain.model.enums.Role;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordEncoder passwordEncoder; // 🔹 Inyección de BCrypt

    /**
     * Construye un usuario de tipo HResources
     */
    public User buildHResources(String nameComplete, String lastnameComplete, String document, String age) {
        User user = new User(nameComplete, lastnameComplete);

        user.setDocument(Long.parseLong(document));
        user.setAge(Integer.parseInt(age));
        user.setRole(Role.HRESOURCES);

        // 🔹 Codifica la contraseña antes de persistir
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return user;
    }

    /**
     * Construye un usuario de tipo Paciente
     */
    public Patient buildAdmin(String nameComplete, String lastnameComplete, String document,
                           String age, String date, String gender, String address,
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
