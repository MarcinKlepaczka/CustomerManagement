package my.microservice.customermanagement.exceptions;

public class CustomerManagementException extends RuntimeException {

	public CustomerManagementException() {
		super("Something went wrong!. See logs for more details. ");
	}

}
