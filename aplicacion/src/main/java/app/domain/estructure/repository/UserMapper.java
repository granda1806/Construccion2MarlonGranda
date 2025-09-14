package app.domain.estructure.repository;

import app.domain.User;

public class UserMapper {
    
    UserEntity UserEntity;
    
    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getDocument(),
                user.getName()
        );
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getDocument(),
                entity.getName()
        );
    }
}
