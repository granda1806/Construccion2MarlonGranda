package app.adapter.out;

import app.domain.model.User;
import app.domain.ports.UserPortOut;

public class UserAdapter implements UserPortOut
{

    @Override
    public User findByDocument(User user) throws Exception
    {
        
        return null;
        
    }

    @Override
    public User findByName(User user) throws Exception
    {
        
        return null;
        
    }

    @Override
    public void save(User user) throws Exception
    {
        
        System.out.println("Se ha creado el usuario.");
        
    }
    
}
