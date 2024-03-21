package org.jsp.ecommerceapp.service;

import org.jsp.ecommerceapp.Exceptions.IdNotFoundException;
import org.jsp.ecommerceapp.Exceptions.InvalidCredentialsException;
import org.jsp.ecommerceapp.Exceptions.MerchantNotFountException;
import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

import java.util.Optional;
import java.util.Random;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;

import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantdao;

	@Autowired
	private ECommerceAppEmailService emailservice;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		structure.setData(merchantdao.saveMerchant(merchant));
		String message = emailservice.sendWelcomeMail(merchant, request);
		structure.setMessage("Merchant saved" + "," + message);
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		Optional<Merchant> recMerchant = merchantdao.findById(merchant.getId());
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setName(merchant.getName());
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPassword(merchant.getPassword());
			structure.setData(merchantdao.saveMerchant(merchant));
			structure.setMessage("merchant updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);

		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		Optional<Merchant> recMerchant = merchantdao.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());

			structure.setMessage("merchant found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.FOUND);

		}
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<Merchant>> findByName(String name) {
		Optional<Merchant> recMerchant = merchantdao.findByName(name);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());

			structure.setMessage("merchant found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.FOUND);

		}
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyByPhone(long phone, String password) {
		Optional<Merchant> recMerchant = merchantdao.verifyByPhone(phone, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("merchant verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("invalid phone");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyByEmail(String email, String password) {
		Optional<Merchant> recMerchant = merchantdao.verifyByEmail(email, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			
			Merchant merchant = recMerchant.get();
			if (merchant.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account is Not Activated");
			}
			structure.setData(recMerchant.get());
			structure.setMessage("merchant verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("invalid credentials");
	}

	public ResponseEntity<ResponseStructure<String>> activate(String token) {
		Optional<Merchant> recmerchant = merchantdao.findByToken(token);
		ResponseStructure<String> struct = new ResponseStructure<>();

		if (recmerchant.isPresent()) {
			Merchant merchant = recmerchant.get();
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			merchant.setToken(null);

			merchantdao.saveMerchant(merchant);

			struct.setData("merchant found");
			struct.setMessage("Account verified and activated ");
			struct.setStatusCode(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<String>>(struct, HttpStatus.ACCEPTED);
		}
		throw new MerchantNotFountException("Invalid url");

	}

}
