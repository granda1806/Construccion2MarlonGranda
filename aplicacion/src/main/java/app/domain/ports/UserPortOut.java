package app.domain.ports;

import app.domain.model.User;

public interface UserPortOut
{
    public User findByDocument(User user) throws Exception;
    public User findByName(User user) throws Exception;
    public void save(User user) throws Exception;
    
}
