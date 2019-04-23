package my.microservice.customermanagement.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import my.microservice.customermanagement.core.util.CustomerManagementMapper;
import my.microservice.customermanagement.persistence.entity.Customer;
import my.microservice.customermanagement.persistence.repository.CustomerRepository;
import my.microservice.customermanagement.types.CustomerTO;

@RunWith(SpringRunner.class)
@Import({CustomerManagementImpl.class, CustomerManagementMapper.class})
/**
 *
 * Test Class for @link CustomerManagement Bean
 *
 */
public class CustomerManagementTest {

	private static final String CUSTOMER_NAME = "Customer";
	private static final String EMAIL = "xx@xx.pl";

	@Autowired
	private CustomerManagement customerManagement;

	@MockBean
	private CustomerRepository customerRepository;

	@Test
	public void findAll() {
		//Act
		customerManagement.findCustomers();

		//Assert
		Mockito.verify(customerRepository).findAll();
	}

	@Test
	public void findCustomer() {
		//Arrange
		Customer customer = new Customer();
		customer.setName(CUSTOMER_NAME);
		customer.setId(12L);
		customer.setEmail(EMAIL);

		when(customerRepository.findCustomerByName(CUSTOMER_NAME)).thenReturn(customer);

		//Act
		CustomerTO foundCustomer = customerManagement.findCustomer(CUSTOMER_NAME);

		//Assert
		verify(customerRepository).findCustomerByName(CUSTOMER_NAME);

		assertThat(foundCustomer).isNotNull();
		assertThat(foundCustomer.getName()).isEqualTo(CUSTOMER_NAME);
		assertThat(foundCustomer.getEmail()).isEqualTo(EMAIL);
	}

	//TODO More tests...
}
