package app.domain.services;

import app.domain.model.User;
import app.domain.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.ports.UserPortIn;

@Service
public class CreateUser
{
    
    @Autowired
    private UserPortIn userPort;
    
    public void create(User user) throws Exception
    {
        
        if (userPort.findByDocument(user) != null)
        {
            
            System.out.println("Ya hay un usuario registrado con este documento.");
            
        }
        
        if (userPort.findByName(user) != null)
        {
            
            System.out.println("Ya hay una persona registrada con este nombre de usuario");
            
        }
        
        userPort.save(user);
        
    }
    
}
