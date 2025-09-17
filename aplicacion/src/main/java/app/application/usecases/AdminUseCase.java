package app.application.usecases;

import app.domain.model.enums.Role;
import app.domain.model.User;
import app.domain.services.CreateUser;

public class AdminUseCase
{
    
    private CreateUser createUSer;
    
    public void createPatient(User user) throws Exception
    {
        
        user.setRole(Role.PATIENT);
        createUSer.create(user);
        
    }
    
}
