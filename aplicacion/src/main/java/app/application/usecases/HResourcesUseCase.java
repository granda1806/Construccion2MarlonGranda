package app.application.usecases;

import app.domain.model.User;
import app.domain.model.enums.Role;
import app.domain.services.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HResourcesUseCase
{
    
    @Autowired
    private CreateUser createUser;
    
    public void createAdmin(User user) throws Exception
    {
        
        user.setRole(Role.ADMIN);
        createUser.create(user);
        
    }
    
    public void createSupport(User user) throws Exception
    {
        
        user.setRole(Role.SUPPORT);
        createUser.create(user);
        
    }
    
    public void createNurse(User user) throws Exception
    {
        
        user.setRole(Role.NURSE);
        createUser.create(user);
        
    }
    
    public void createDoctor(User user) throws Exception
    {
        
        user.setRole(Role.DOCTOR);
        createUser.create(user);
        
    }
    
    public void createHResources(User user) throws Exception
    {
        
        user.setRole(Role.HRESOURCES);
        createUser.create(user);
        
    }
    
}
