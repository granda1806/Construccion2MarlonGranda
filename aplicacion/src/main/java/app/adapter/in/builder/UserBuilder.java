
package app.adapter.in.builder;

import app.adapter.in.validators.UserValidator;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class UserBuilder {
    
    @Autowired
    private UserValidator userValidator;
    
    public User buildHResources(String name, String document, String age, String userName, String password)throws Exception{
        User user = new User();
        user.setName(userValidator.nameValidator(name));
        user.setDocument(userValidator.documentValidator(document));
        user.setAge(userValidator.ageValidator(age));
        user.setUserName(userValidator.userNameValidator(userName));
        user.setPassword(userValidator.passwordValidator(password));
        return user;
    }
    
    public User buildAdmin(String name, String document, String age, String date, String gender, String addres, String contactName, String relationship, String contactNumber)throws Exception{
        User user = new User();
        user.setName(userValidator.nameValidator(name));
        user.setDocument(userValidator.documentValidator(document));
        user.setAge(userValidator.ageValidator(age));
        /*user.setDate(userValidator.dateValidator(date));*/
        user.setGender(userValidator.genderValidator(gender));
        user.setAddres(userValidator.addresValidator(addres));
        user.setEmergencyContactName(userValidator.contactNameValidator(contactName));
        user.setRelationshipPatient(userValidator.relationshipValidator(relationship));
        user.setEmergencyContactNumber(userValidator.contactNumberValidator(contactNumber));
        return user;
    }
}
