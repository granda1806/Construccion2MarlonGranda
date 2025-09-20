package app.domain.ports;

import app.domain.model.User;

public interface UserPortIn {
	public User findByDocument(User user) throws Exception;
	public User findByUserName(User user) throws Exception;
	public void save(User user) throws Exception;
}

