package org.jsp.ecommerceapp.Repository;


import java.util.Optional;


import org.jsp.ecommerceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository  extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.name=?1")
	public Optional<User> findByName(String name);

	@Query("select u from User u where u.phone=?1 and u.password=?2")
	public Optional<User> verifyByPhone(long phone, String password);

	@Query("select u from User  u where u.email=?1 and u.password=?2")
	public Optional<User> verifyByEmail(String email, String password);

}
