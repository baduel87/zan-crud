package it.zan.service;

import java.util.List;

import it.zan.model.User;

public interface UserService {
	
	Integer save(User user);
	User get(Integer id);
	List<User> list();
	void update(Integer id, User user);
	void delete(Integer id);

}
