package app.domain.services;

import app.domain.model.User;
import app.domain.model.enums.Role;
import app.domain.ports.UserPortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {
    
    @Autowired
    private UserPortOut userPortIn;

    public void create (User user) throws Exception {

        if (userPortIn.findByDocument(user) != null) {
            throw new Exception("Ya hay un usuario registrado con este documento");
        }

        if (!user.getRole().equals(Role.ADMIN) && userPortIn.findByName(user) != null) {
            throw new Exception("ya existe una persona registrada con ese nombre de usuario");
	}
            userPortIn.save(user);
    }
}

