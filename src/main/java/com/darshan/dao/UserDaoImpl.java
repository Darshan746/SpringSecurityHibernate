package com.darshan.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.darshan.bean.User;

@Repository
@Transactional(readOnly=true)
public class UserDaoImpl implements UserDao {

	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByUserName(String username) {
		List<User> users = new ArrayList<User>();

		try {
			users = sessionFactory.openSession()
				.createQuery("from User u  where u.username=:username")
				.setParameter("username", username)
				.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
