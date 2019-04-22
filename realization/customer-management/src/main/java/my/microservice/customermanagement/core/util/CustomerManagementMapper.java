package my.microservice.customermanagement.core.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import my.microservice.customermanagement.persistence.entity.Address;
import my.microservice.customermanagement.persistence.entity.Customer;
import my.microservice.customermanagement.types.AddressTO;
import my.microservice.customermanagement.types.CustomerTO;

/**
 *
 * Class providing methods to map TO objects to Entites and vice-versa.
 * (Normally it would be realized by some mapping library).
 *
 */

@Component
public class CustomerManagementMapper {

	public Customer mapCustomer(CustomerTO to) {
		Customer customer = new Customer();
		customer.setName(to.getName());
		customer.setEmail(to.getEmail());
		customer.setPhone(to.getPhone());
		customer.setAddresses(mapAddressesList(to.getAddresses()));
		return customer;
	}

	public CustomerTO mapCustomerTO(Customer ety) {
		if (ety == null) {
			return null;
		}

		CustomerTO to = new CustomerTO();
		to.setName(ety.getName());
		to.setEmail(ety.getEmail());
		to.setPhone(ety.getPhone());
		to.setAddresses(mapAddressesTOList(ety.getAddresses()));

		return to;
	}

	public List<AddressTO> mapAddressesTOList(List<Address> addresses) {
		if (addresses == null) {
			return null;
		}
		return addresses.stream()
				.map(this::mapAddressTO)
				.collect(Collectors.toList());
	}

	public List<Address> mapAddressesList(List<AddressTO> addresses) {
		if (addresses == null) {
			return null;
		}
		return addresses.stream()
				.map(this::mapAddress)
				.collect(Collectors.toList());
	}

	public AddressTO mapAddressTO(Address ety) {
		if (ety == null) {
			return null;
		}

		AddressTO address = new AddressTO();
		address.setCity(ety.getCity());
		address.setCountry(ety.getCountry());
		address.setHouseNumber(ety.getHouseNumber());
		address.setStreet(ety.getStreet());
		address.setZipCode(ety.getZipCode());

		return address;
	}

	public Address mapAddress(AddressTO to) {

		Address address = new Address();
		address.setCity(to.getCity());
		address.setCountry(to.getCountry());
		address.setHouseNumber(to.getHouseNumber());
		address.setStreet(to.getStreet());
		address.setZipCode(to.getZipCode());

		return address;
	}

}
