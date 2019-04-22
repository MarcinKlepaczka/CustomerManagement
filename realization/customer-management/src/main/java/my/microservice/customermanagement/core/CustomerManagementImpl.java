package my.microservice.customermanagement.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.microservice.customermanagement.core.util.CustomerManagementMapper;
import my.microservice.customermanagement.persistence.entity.Address;
import my.microservice.customermanagement.persistence.entity.Customer;
import my.microservice.customermanagement.persistence.repository.CustomerRepository;
import my.microservice.customermanagement.types.AddressTO;
import my.microservice.customermanagement.types.CustomerTO;

@Component
public class CustomerManagementImpl implements CustomerManagement {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerManagementMapper mapper;

	@Override
	@Transactional
	public List<CustomerTO> findCustomers() {
		Iterable<Customer> customers = customerRepository.findAll();

		return StreamSupport.stream(customers.spliterator(), false)
				.map(c -> mapper.mapCustomerTO(c))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerTO findCustomer(String customerName) {
		Customer customer = customerRepository.findCustomerByName(customerName);
		return mapper.mapCustomerTO(customer);
	}

	@Override
	public CustomerTO addCustomer(CustomerTO newCustomer) {
		Customer customer = mapper.mapCustomer(newCustomer);
		customer = customerRepository.save(customer);
		return mapper.mapCustomerTO(customer);
	}

	@Override
	@Transactional
	public CustomerTO addAddressToCustomer(String customerName, AddressTO address) {
		Customer customer = customerRepository.findCustomerByName(customerName);

		if (customer == null) {
			throw new RuntimeException("Customer with that name does not exist!");
		}

		Address newAddress = mapper.mapAddress(address);
		if (customer.getAddresses() == null) {
			customer.setAddresses(new ArrayList<>());
		}
		customer.getAddresses().add(newAddress);

		customer = customerRepository.save(customer);
		return mapper.mapCustomerTO(customer);

	}

}
