package app.domain.services;

import app.domain.model.User;
import app.domain.ports.UserPortOut;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {

    private final UserPortOut userPortOut;

    public CreateUser(UserPortOut userPortOut) {
        this.userPortOut = userPortOut;
    }

    public User create(User user) throws Exception {
        userPortOut.save(user);
        return user;
    }
}


