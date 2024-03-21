package org.jsp.ecommerceapp.dao;

import java.util.Optional;

import org.jsp.ecommerceapp.Repository.UserRepository;

import org.jsp.ecommerceapp.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userrepo;

	public User saveuser(User user) {
		return userrepo.save(user);
	}

	public User updateUser(User User) {
		return userrepo.save(User);

	}

	public Optional<User> findById(int id) {

		return userrepo.findById(id);
	}

	public Optional<User> findByName(String name) {
		return userrepo.findByName(name);
	}

	public Optional<User> verifyByPhone(long phone, String password) {
		return userrepo.verifyByPhone(phone, password);
	}

	public Optional<User> verifyByEmail(String email, String password) {
		return userrepo.verifyByEmail(email, password);
	}

}
