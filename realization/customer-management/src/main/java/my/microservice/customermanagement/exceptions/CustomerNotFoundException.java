package my.microservice.customermanagement.exceptions;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {
		super("There is no customer with provided name.");
	}

}
