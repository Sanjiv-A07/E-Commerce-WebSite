package org.jsp.ecommerceapp.Exceptions;

public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "invalid id";
	}

}
