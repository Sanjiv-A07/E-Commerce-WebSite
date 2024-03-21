package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.Exceptions.IdNotFoundException;
import org.jsp.ecommerceapp.Exceptions.InvalidCredentialsException;
import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userdao;

	public ResponseEntity<ResponseStructure<User>> saveuser(User user) {
		ResponseStructure<User> struct = new ResponseStructure<>();
		struct.setData(userdao.saveuser(user));
		struct.setMessage("user Saved");
		struct.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<User>>(struct, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User User) {
		Optional<User> recUser = userdao.findById(User.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setName(User.getName());
			dbUser.setEmail(User.getEmail());
			dbUser.setPhone(User.getPhone());
			dbUser.setAge(User.getAge());
			dbUser.setPassword(User.getPassword());
			structure.setData(userdao.saveuser(User));
			structure.setMessage("User updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);

		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> recUser = userdao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());

			structure.setMessage("User found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);

		}
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<User>> findByName(String name) {
		Optional<User> recUser = userdao.findByName(name);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());

			structure.setMessage("User found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);

		}
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<User>> verifyByPhone(long phone, String password) {
		Optional<User> recUser = userdao.verifyByPhone(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("invalid phone");
	}

	public ResponseEntity<ResponseStructure<User>> verifyByEmail(String email, String password) {
		Optional<User> recUser = userdao.verifyByEmail(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("invalid credentials");
	}

}
