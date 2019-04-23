package my.microservice.customermanagement.core;

import java.util.List;

import my.microservice.customermanagement.types.AddressTO;
import my.microservice.customermanagement.types.CustomerTO;

/**
 * Component that is representing business logic for Customer Management API
 *
 */
public interface CustomerManagement {

	/**
	 *  Return all the existing customers
	 *  @return Customer list
	 */
	public List<CustomerTO> findCustomers();

	/**
	 *  Finds customer with a provided name
	 *  @param customerName name of the customer
	 *  @return Customer if exists
	 */
	public CustomerTO findCustomer(String customerName);

	/**
	 *  Saves new customer.
	 *  @param newCustomer customer to save.
	 *  @return saved Customer
	 */
	public CustomerTO addCustomer(CustomerTO newCustomer);

	/**
	 *  Adds address to a customer
	 *  @param customerName name of the customer
	 *  @param address address to add
	 *  @return updated customer
	 */
	public CustomerTO addAddressToCustomer(String customerName, AddressTO address);
}
