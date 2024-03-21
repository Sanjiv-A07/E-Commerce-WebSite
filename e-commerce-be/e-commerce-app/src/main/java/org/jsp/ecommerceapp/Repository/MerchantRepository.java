package org.jsp.ecommerceapp.Repository;

import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	@Query("select m from Merchant m where m.name=?1")
	 Optional<Merchant> findByName(String name);

	@Query("select m from Merchant m where m.phone=?1 and m.password=?2")
	 Optional<Merchant> verifyByPhone(long phone, String password);

	@Query("select m from Merchant  m where m.email=?1 and m.password=?2")
	 Optional<Merchant> verifyByEmail(String email, String password);
	
	
	 Optional<Merchant> findByToken(String token);
	

}
