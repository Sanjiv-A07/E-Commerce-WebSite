package org.jsp.ecommerceapp.dao;


import org.jsp.ecommerceapp.Repository.MerchantRepository;
import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import java.util.Optional;



@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository merchantRepository;
	
	public Merchant saveMerchant(Merchant merchant)
	{
		return merchantRepository.save(merchant);
		
	}
	public Merchant updateMerchant(Merchant merchant)
	{
		return merchantRepository.save(merchant);
		
	}
	public Optional<Merchant> findById(int id) {
		
		return merchantRepository.findById(id);
	}
	public Optional<Merchant> findByName(String name)
	{
		return merchantRepository.findByName(name);
	}
	public Optional<Merchant> verifyByPhone(long phone,String password)
	{
		return merchantRepository.verifyByPhone(phone,password);
	}
	public Optional<Merchant> verifyByEmail(String email,String password)
	{
		return merchantRepository.verifyByEmail(email,password);
	}
	
	public Optional<Merchant> findByToken(String token){
		return merchantRepository.findByToken(token);
	}

	

}
	
	


