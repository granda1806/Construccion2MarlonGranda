
package app.domain.services;

import app.domain.model.User;
import app.domain.ports.UserPort;
public class CreateUser {
    
    private UserPort userPort;
    public void cretae(User user) throws Exception {
        if (userPort.findByDocument(user) != null) {
            System.out.println("Ya hay un usuario registrado con este documento.");
        }
        
        if (userPort.findByName(user) != null) {
            System.out.println("Ya hay una persona registrada con este nombre de usuario");
        }
        userPort.save(user);
    }    
}
