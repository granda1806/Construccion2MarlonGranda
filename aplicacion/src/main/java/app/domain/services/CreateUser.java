package app.domain.services;

import app.domain.model.User;
import app.domain.ports.UserPortIn;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {

    private final UserPortIn userPortIn;

    public CreateUser(UserPortIn userPortIn) {
        this.userPortIn = userPortIn;
    }

    public User create(User user) {
        Long document = user.getDocument();
        String username = user.getName();

        if (userPortIn.existsByDocument(document)) {
            throw new IllegalStateException("Ya hay un usuario registrado con este documento: " + document);
        }

        if (userPortIn.findByName(username).isPresent()) {
            throw new IllegalStateException("Ya hay una persona registrada con este nombre de usuario: " + username);
        }
        return userPortIn.save(user);
    }
}

