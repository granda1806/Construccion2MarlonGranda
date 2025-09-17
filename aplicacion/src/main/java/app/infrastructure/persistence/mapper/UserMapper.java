package app.infrastructure.persistence.mapper;

import app.domain.model.User;
import app.infrastructure.persistence.entities.UserEntity;

public class UserMapper
{
    
    public static UserEntity toEntity(User user)
    {
        
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setDocument(user.getDocument());
        entity.setName(user.getName());
        entity.setUser(user.getUserName());
        entity.setPassword(user.getPassword());
        entity.setAge(user.getAge());
        entity.setEmail(user.getEmail());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setDate(user.getDate());
        entity.setRole(user.getRole());
        entity.setAddress(user.getAddres());
        entity.setGender(user.getGender());
        entity.setEmergencyContactName(user.getEmergencyContactName());
        entity.setEmergencyContactNumber(user.getEmergencyContactNumber());
        entity.setRelationshipPatient(user.getRelationshipPatient());
        return entity;           
         
    }

    public static User toDomain(UserEntity entity)
    {
                if (entity == null) return null;
                User user = new User();
                user.setId(entity.getId());
                user.setDocument(entity.getDocument());
                user.setName(entity.getName());
                user.setUserName(entity.getUser());
                user.setPassword(entity.getPassword());
                user.setAge(entity.getAge());
                user.setEmail(entity.getEmail());
                user.setPhoneNumber(entity.getPhoneNumber());
                user.setDate(entity.getDate());
                user.setRole(entity.getRole());
                user.setAddres(entity.getAddress());
                user.setGender(entity.getGender());
                user.setEmergencyContactName(entity.getEmergencyContactName());
                user.setEmergencyContactNumber(entity.getEmergencyContactNumber());
                user.setRelationshipPatient(entity.getRelationshipPatient());
                return user;
                
    }
    
}
