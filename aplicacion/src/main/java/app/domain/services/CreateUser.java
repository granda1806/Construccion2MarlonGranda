package app.domain.services;

import app.domain.model.User;
import app.domain.ports.UserPortIn;
import app.domain.ports.UserPortOut;
import org.springframework.stereotype.Service;

@Service
public class CreateUser implements UserPortIn {

    private final UserPortOut userPortOut;

    public CreateUser(UserPortOut userPortOut) {
        this.userPortOut = userPortOut;
    }

    @Override
    public User findByDocument(User user) throws Exception {
        return userPortOut.findByDocument(user);
    }

    @Override
    public User findByName(User user) throws Exception {
        return userPortOut.findByName(user);
    }

    @Override
    public void save(User user) throws Exception {
        if (userPortOut.findByDocument(user) != null) {
            System.out.println("Ya hay un usuario registrado con este documento.");
            return;
        }
        if (userPortOut.findByName(user) != null) {
            System.out.println("Ya hay una persona registrada con este nombre de usuario");
            return;
        }
        userPortOut.save(user);
    }
}

