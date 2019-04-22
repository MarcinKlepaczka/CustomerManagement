package my.microservice.customermanagement.core;

import java.util.List;

import my.microservice.customermanagement.types.AddressTO;
import my.microservice.customermanagement.types.CustomerTO;

public interface CustomerManagement {

	public List<CustomerTO> findCustomers();

	public CustomerTO findCustomer(String customerName);

	public CustomerTO addCustomer(CustomerTO newCustomer);

	public CustomerTO addAddressToCustomer(String customerName, AddressTO address);
}
