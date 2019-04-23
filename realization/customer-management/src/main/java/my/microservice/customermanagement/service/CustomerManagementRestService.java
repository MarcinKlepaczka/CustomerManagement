package my.microservice.customermanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import my.microservice.customermanagement.constant.Roles;
import my.microservice.customermanagement.core.CustomerManagement;
import my.microservice.customermanagement.types.AddressRequestTO;
import my.microservice.customermanagement.types.CustomerTO;

@RestController
@RequestMapping("/api")
@Secured(Roles.ADMIN)
public class CustomerManagementRestService {

	@Autowired
	private CustomerManagement customerManagement;


    @ApiOperation(
    	    value = "List all customers",
    	    response = CustomerTO.class,
    	    responseContainer = "List",
    	    httpMethod = "GET"
	)
    @RequestMapping(path = "/getCustomers", method = { RequestMethod.GET })
    public @ResponseBody List<CustomerTO> findAll() {
    	return customerManagement.findCustomers();
    }

    @ApiOperation(
    	    value = "Add new customer",
    	    response = CustomerTO.class,
    	    httpMethod = "POST"
	)
    @RequestMapping(path = "/addCustomer", method = { RequestMethod.POST })
    public @ResponseBody CustomerTO addCustomer(@Valid @RequestBody CustomerTO newCustomer) {
     	return customerManagement.addCustomer(newCustomer);
   }


    @ApiOperation(
    	    value = "Add new address to customer",
    	    response = CustomerTO.class,
    	    httpMethod = "POST"
	)
    @RequestMapping(path = "/addAddress", method = { RequestMethod.POST })
    @Transactional
    public CustomerTO addAddress(@Valid @RequestBody AddressRequestTO addressRequest) {
    	return customerManagement.addAddressToCustomer(addressRequest.getCustomerName(), addressRequest.getAddress());
   }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
