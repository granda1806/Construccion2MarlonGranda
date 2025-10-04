package app.adapter.in.builder;

import app.adapter.in.validators.UserValidator;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    @Autowired
    private UserValidator userValidator;

    /**
     * Construye un usuario de tipo HResources
     */
    public User buildHResources(String nameComplete, String lastnameComplete, String document,
                                String age, String userName, String password) throws Exception {

        User user = new User();
        user.setNameComplete(userValidator.nameValidator(nameComplete));
        user.setLastnameComplete(userValidator.nameValidator(lastnameComplete));
        user.setDocument(userValidator.documentValidator(document));
        user.setAge(userValidator.ageValidator(age));
        user.setUserName(userValidator.userNameValidator(userName));
        user.setPassword(userValidator.passwordValidator(password));

        return user;
    }

    /**
     * Construye un usuario de tipo Admin
     */
    public User buildAdmin(String nameComplete, String lastnameComplete, String document,
                           String age, String date, String gender, String address,
                           String contactName, String genderContact, String relationship,
                           String contactNumber, String userName, String password) throws Exception {

        User user = new User();
        user.setNameComplete(userValidator.nameValidator(nameComplete));
        user.setLastnameComplete(userValidator.nameValidator(lastnameComplete));
        user.setDocument(userValidator.documentValidator(document));
        user.setAge(userValidator.ageValidator(age));
        user.setDate(userValidator.dateValidator(date));
        user.setGender(userValidator.genderValidator(gender));
        user.setAddress(userValidator.addressValidator(address));
        user.setEmergencyContactName(userValidator.contactNameValidator(contactName));
        user.setGenderEmergencyContact(userValidator.genderValidator(genderContact));
        user.setRelationshipPatient(userValidator.relationshipValidator(relationship));
        user.setEmergencyContactNumber(userValidator.contactNumberValidator(contactNumber));
        user.setUserName(userValidator.userNameValidator(userName));
        user.setPassword(userValidator.passwordValidator(password));

        return user;
    }
}
