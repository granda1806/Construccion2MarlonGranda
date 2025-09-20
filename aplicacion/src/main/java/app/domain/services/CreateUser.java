package app.domain.services;

import app.domain.model.User;
import app.domain.model.enums.Role;
import app.domain.ports.UserPortIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {
    
    @Autowired
    private UserPortIn userPortIn;

    public void create (User user) throws Exception {

        if (userPortIn.findByDocument(user) != null) {
            throw new Exception("Ya hay un usuario registrado con este documento");
        }

        if (!user.getRole().equals(Role.ADMIN) && userPortIn.findByUserName(user) != null) {
            throw new Exception("ya existe una persona registrada con ese nombre de usuario");
	}
        
        if (!user.getRole().equals(Role.DOCTOR) && userPortIn.findByUserName(user) != null) {
            throw new Exception("ya existe una persona registrada con ese nombre de usuario");
	}
        
        if (!user.getRole().equals(Role.HRESOURCES) && userPortIn.findByUserName(user) != null) {
            throw new Exception("ya existe una persona registrada con ese nombre de usuario");
	}
        
        if (!user.getRole().equals(Role.NURSE) && userPortIn.findByUserName(user) != null) {
            throw new Exception("ya existe una persona registrada con ese nombre de usuario");
	}
        if (!user.getRole().equals(Role.PATIENT) && userPortIn.findByUserName(user) != null) {
            throw new Exception("ya existe una persona registrada con ese nombre de usuario");
	}
        
        if (!user.getRole().equals(Role.SUPPORT) && userPortIn.findByUserName(user) != null) {
            throw new Exception("ya existe una persona registrada con ese nombre de usuario");
	}
            userPortIn.save(user);
    }
}

