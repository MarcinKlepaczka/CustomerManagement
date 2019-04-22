package my.microservice.customermanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import my.microservice.customermanagement.persistence.entity.Address;
import my.microservice.customermanagement.persistence.entity.Customer;
import my.microservice.customermanagement.persistence.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {


	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void saveCustomer() {

		//Arrange
		Customer customer = new Customer();
		customer.setName("Test Customer");
		customer.setPhone("+48123456789");
		customer.setEmail("xx@xx.pl");

		//Act
		Customer savedCustomer = customerRepository.save(customer);

		//Assert
		assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isNotNull();

		Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);

		assertThat(foundCustomer).isNotNull();
		assertThat(foundCustomer.getId()).isNotNull();
		assertThat(foundCustomer.getName()).isEqualTo(customer.getName());
		assertThat(foundCustomer.getPhone()).isEqualTo(customer.getPhone());
		assertThat(foundCustomer.getEmail()).isEqualTo(customer.getEmail());

	}

	@Test
	@Transactional
	public void saveCustomerWithAddresses() {

		//Arrange
		Customer customer = new Customer();
		customer.setName("Test Customer2");

		Address address1 = new Address();
		address1.setCity("Wroclaw");
		address1.setCountry("Poland");
		address1.setHouseNumber("12/12");
		address1.setStreet("Piekna");

		Address address2 = new Address();
		address2.setCity("Wroclaw");
		address2.setCountry("Poland");
		address2.setHouseNumber("13/13");
		address2.setStreet("Piekna");

		customer.setAddresses(Arrays.asList(address1, address2));

		//Act
		Customer savedCustomer = customerRepository.save(customer);

		//Assert
		assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isNotNull();

		Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);

		assertThat(foundCustomer).isNotNull();
		assertThat(foundCustomer.getId()).isNotNull();
		assertThat(foundCustomer.getAddresses()).isNotEmpty();
		assertThat(foundCustomer.getAddresses().size()).isEqualTo(2);

		Address firstSavedAddress = foundCustomer.getAddresses().get(0);

		assertThat(firstSavedAddress.getId()).isNotNull();
		assertThat(firstSavedAddress.getCity()).isEqualTo(address1.getCity());
		assertThat(firstSavedAddress.getCountry()).isEqualTo(address1.getCountry());
		assertThat(firstSavedAddress.getHouseNumber()).isEqualTo(address1.getHouseNumber());
		assertThat(firstSavedAddress.getZipCode()).isEqualTo(address1.getZipCode());

		Address secondSavedAddress = foundCustomer.getAddresses().get(1);

		assertThat(secondSavedAddress.getId()).isNotNull();
		assertThat(secondSavedAddress.getCity()).isEqualTo(address2.getCity());
		assertThat(secondSavedAddress.getCountry()).isEqualTo(address2.getCountry());
		assertThat(secondSavedAddress.getHouseNumber()).isEqualTo(address2.getHouseNumber());
		assertThat(secondSavedAddress.getZipCode()).isEqualTo(address2.getZipCode());

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void saveCustomerWithoutName() {

		//Arrange
		Customer customer = new Customer();
		customer.setPhone("+48123456789");
		customer.setEmail("xx@xx.pl");

		//Act
		customerRepository.save(customer);

		//Assert
		fail();

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void saveCustomerWithAlreadyExistingName() {

		//Arrange
		Customer customer = new Customer();
		customer.setName("Customer");

		//Act
		customerRepository.save(customer);

		Customer customer2 = new Customer();
		customer2.setName("Customer");

		customerRepository.save(customer2);

		//Assert
		fail();

	}

	@Test
	@Transactional
	public void findAllCustomers() {

		//Arrange
		Customer customer = new Customer();
		customer.setName("Customer");

		Customer customer2 = new Customer();
		customer2.setName("Customer2");

		Customer customer3 = new Customer();
		customer3.setName("Customer3");

		Address address = new Address();
		address.setCity("Wroclaw");
		customer3.setAddresses(Arrays.asList(address));


		customerRepository.saveAll(Arrays.asList(customer,customer2, customer3));

		//Act
		Iterable<Customer> foundCustomers = customerRepository.findAll();

		//Assert
		List<String> names = StreamSupport.stream(foundCustomers.spliterator(), false)
				.map(c -> c.getName())
				.collect(Collectors.toList());

		assertThat(names).containsExactlyInAnyOrder("Customer","Customer2","Customer3");

		List<String> cities = StreamSupport.stream(foundCustomers.spliterator(), false)
				.filter(c -> Objects.nonNull(c.getAddresses()))
				.flatMap(c -> c.getAddresses().stream())
				.map(a -> a.getCity())
				.collect(Collectors.toList());

		assertThat(cities).containsExactlyInAnyOrder("Wroclaw");
	}




}
