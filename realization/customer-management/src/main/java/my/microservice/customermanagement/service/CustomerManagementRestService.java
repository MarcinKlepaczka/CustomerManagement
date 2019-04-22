package my.microservice.customermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.microservice.customermanagement.persistence.entity.Address;
import my.microservice.customermanagement.persistence.entity.Customer;
import my.microservice.customermanagement.persistence.repository.CustomerRepository;
import my.microservice.customermanagement.types.AddressRequestTO;
import my.microservice.customermanagement.types.AddressTO;
import my.microservice.customermanagement.types.CustomerTO;

@RestController
@RequestMapping("/api")
@Secured("ROLE_USER")
public class CustomerManagementRestService {

	@Autowired
	CustomerRepository customerRepository;

    @GetMapping
    @RequestMapping("/getCustomers")
    public List<CustomerTO> findAll() {

    	Iterable<Customer> foundCustomers = customerRepository.findAll();
    	return StreamSupport.stream(foundCustomers.spliterator(), false).map(this::mapToCustomerTO).collect(Collectors.toList());
    }

    @PutMapping
    @RequestMapping("/addCustomer")
    public void addCustomer(@RequestBody CustomerTO newCustomer) {

    	Customer customer = mapCustomer(newCustomer);
    	customerRepository.save(customer);

   }

    @PutMapping
    @RequestMapping("/addAddress")
    @Transactional
    public void addAddress(@RequestBody AddressRequestTO addressRequest) {

    	Customer customer = customerRepository.findCustomerByName(addressRequest.getCustomerName());

    	Address address = mapAddress(addressRequest.getAddress());

    	if (customer.getAddresses() == null) {
    		customer.setAddresses(new ArrayList<>());
    	}
    	customer.getAddresses().add(address);

    	customerRepository.save(customer);

   }

	private Customer mapCustomer(CustomerTO newCustomer) {
		Customer to = new Customer();
		to.setName(newCustomer.getName());
		to.setEmail(newCustomer.getEmail());
		to.setPhone(newCustomer.getPhone());
		return to;
	}

	private CustomerTO mapToCustomerTO(Customer c) {
		CustomerTO to = new CustomerTO();
		to.setName(c.getName());
		to.setEmail(c.getEmail());
		to.setPhone(c.getPhone());
		to.setAddresses(mapAddresses(c.getAddresses()));

		return to;
	}

	private List<AddressTO> mapAddresses(List<Address> addresses) {
		return addresses.stream().map(this::mapAddressTO).collect(Collectors.toList());
	}

	private AddressTO mapAddressTO(Address a) {
		AddressTO address = new AddressTO();
		address.setCity(a.getCity());
		address.setCountry(a.getCountry());
		address.setHouseNumber(a.getHouseNumber());
		address.setStreet(a.getStreet());
		address.setZipCode(a.getZipCode());

		return address;
	}

	private Address mapAddress(AddressTO a) {
		Address address = new Address();
		address.setCity(a.getCity());
		address.setCountry(a.getCountry());
		address.setHouseNumber(a.getHouseNumber());
		address.setStreet(a.getStreet());
		address.setZipCode(a.getZipCode());

		return address;
	}
}
