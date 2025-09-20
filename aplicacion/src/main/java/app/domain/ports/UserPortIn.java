package app.domain.ports;

import app.domain.model.User;
import java.util.Optional;

public interface UserPortIn {

    Optional<User> findByDocument(String document);

    Optional<User> findByName(String name);

    boolean existsByDocument(Long document);

    User save(User user);
}

