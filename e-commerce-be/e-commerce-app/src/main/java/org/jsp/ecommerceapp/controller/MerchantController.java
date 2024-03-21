package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/merchant")
public class MerchantController {
	@Autowired
	private MerchantService merchantservice;

	@PostMapping
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant,
			HttpServletRequest request) {
		return merchantservice.saveMerchant(merchant, request);
	}


 
	@PutMapping
	private ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return merchantservice.updateMerchant(merchant);
	}

	@GetMapping("/{id}")
	private ResponseEntity<ResponseStructure<Merchant>> findByID(@PathVariable int id) {
		return merchantservice.findById(id);

	}

	@GetMapping("/name/{name}")
	private ResponseEntity<ResponseStructure<Merchant>> findByName(@PathVariable String name) {
		return merchantservice.findByName(name);

	}

	@PostMapping("/verify-by-phone")
	private ResponseEntity<ResponseStructure<Merchant>> verifyByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return merchantservice.verifyByPhone(phone, password);
	}

	@PostMapping("/verify-by-email")
	private ResponseEntity<ResponseStructure<Merchant>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		return merchantservice.verifyByEmail(email, password);
	}
	@GetMapping("/activate")
	public ResponseEntity<ResponseStructure<String>> activate(@RequestParam String token) {
		return merchantservice.activate(token);
	}
	
	

}
