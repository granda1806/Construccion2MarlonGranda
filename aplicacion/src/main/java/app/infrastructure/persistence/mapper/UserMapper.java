package app.infrastructure.persistence.mapper;

import app.domain.model.User;
import app.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        return new UserEntity(
                null,
                user.getNameComplete(),
                user.getDocument(),
                user.getAge(),
                user.getRole(),
                user.getUserName(),
                user.getPassword()
        );
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setDocument(entity.getDocument());
        user.setAge(entity.getAge());
        user.setRole(entity.getRole());
        user.setUserName(entity.getUserName());
        user.setPassword(entity.getPassword());
        return user;
    }
}

