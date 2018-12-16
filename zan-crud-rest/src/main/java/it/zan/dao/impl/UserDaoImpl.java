package it.zan.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.zan.dao.UserDao;
import it.zan.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Integer save(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user.getId();
	}

	public User get(Integer id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	public List<User> list() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		Query<User> query = session.createQuery(cq);
		return query.getResultList();
	}

	public void update(Integer id, User user) {
		Session session = sessionFactory.getCurrentSession();
		User userToPass = session.byId(User.class).load(id);
		userToPass.setFirstName(user.getFirstName());
		userToPass.setLastName(user.getLastName());
		userToPass.setDateOfBirth(user.getDateOfBirth());
		session.flush();
	}

	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.byId(User.class).load(id);
		session.delete(user);
	}

}
