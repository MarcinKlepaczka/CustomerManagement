package my.microservice.customermanagement.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import my.microservice.customermanagement.persistence.entity.Customer;

/**
 *
 * Repository that provides standard methods for Customer entity manangement.
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
