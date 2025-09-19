package app.domain.services;

import app.domain.model.User;
import app.domain.ports.UserPortIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {
    
    @Autowired
    private UserPortIn userPortOut;

    public CreateUser(UserPortIn userPortOut) {
        this.userPortOut = userPortOut;
    }

    public void create(User user) throws Exception {

        if (userPortOut.findByDocument(user) != null) {
            System.out.println("Ya hay un usuario registrado con este documento.");
        }

        if (userPortOut.findByName(user) != null) {
            System.out.println("Ya hay una persona registrada con este nombre de usuario");
        }

        userPortOut.save(user);
    }
}