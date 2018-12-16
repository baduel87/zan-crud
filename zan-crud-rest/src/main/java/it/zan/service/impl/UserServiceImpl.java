package it.zan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.zan.dao.UserDao;
import it.zan.model.User;
import it.zan.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public Integer save(User user) {
		return userDao.save(user);
	}

	public User get(Integer id) {
		return userDao.get(id);
	}

	public List<User> list() {
		return userDao.list();
	}

	public void update(Integer id, User user) {
		userDao.update(id, user);
	}

	public void delete(Integer id) {
		userDao.delete(id);
	}

}
