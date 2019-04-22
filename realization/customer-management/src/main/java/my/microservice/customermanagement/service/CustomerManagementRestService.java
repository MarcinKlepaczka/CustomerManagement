package my.microservice.customermanagement.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.microservice.customermanagement.core.CustomerManagement;
import my.microservice.customermanagement.types.AddressRequestTO;
import my.microservice.customermanagement.types.CustomerTO;

@RestController
@RequestMapping("/api")
@Secured("ROLE_USER")
public class CustomerManagementRestService {

	@Autowired
	private CustomerManagement customerManagement;

    @GetMapping
    @RequestMapping("/getCustomers")
    public List<CustomerTO> findAll() {
    	return customerManagement.findCustomers();
    }

    @PostMapping
    @RequestMapping("/addCustomer")
    public @ResponseBody CustomerTO addCustomer(@Valid @RequestBody CustomerTO newCustomer) {
     	return customerManagement.addCustomer(newCustomer);
   }

    @PutMapping
    @RequestMapping("/addAddress")
    @Transactional
    public CustomerTO addAddress(@Valid @RequestBody AddressRequestTO addressRequest) {
    	return customerManagement.addAddressToCustomer(addressRequest.getCustomerName(), addressRequest.getAddress());
   }


}
