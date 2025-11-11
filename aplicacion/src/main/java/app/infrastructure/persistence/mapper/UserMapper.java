package app.infrastructure.persistence.mapper;

import app.domain.model.User;
import app.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Convierte del modelo de dominio a la entidad JPA
    public static UserEntity toEntity(User user) {
        if (user == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(null);
        entity.setNameComplete(user.getNameComplete());
        entity.setLastnameComplete(user.getLastnameComplete());
        entity.setDocument(user.getDocument());
        entity.setAge(user.getAge());
        entity.setRole(user.getRole());
        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());
        return entity;
    }

    // Convierte de la entidad JPA al modelo de dominio
    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        User user = new User();
        user.setId(entity.getId());
        user.setNameComplete(entity.getNameComplete());
        user.setLastnameComplete(entity.getLastnameComplete());
        user.setDocument(entity.getDocument());
        user.setAge(entity.getAge());
        user.setRole(entity.getRole());
        user.setUserName(entity.getUserName());
        user.setPassword(entity.getPassword());
        return user;
    }
}
