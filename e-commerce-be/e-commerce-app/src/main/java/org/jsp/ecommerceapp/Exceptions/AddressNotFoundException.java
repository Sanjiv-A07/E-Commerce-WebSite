package org.jsp.ecommerceapp.Exceptions;

public class AddressNotFoundException  extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AddressNotFoundException(String message) {
		super(message);
	}


}
