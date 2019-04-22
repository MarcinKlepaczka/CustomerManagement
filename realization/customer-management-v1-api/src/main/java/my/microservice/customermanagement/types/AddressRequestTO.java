package my.microservice.customermanagement.types;

import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing request for customer address creation")
public class AddressRequestTO {

    @ApiModelProperty(value = "Name/identifier of the customer", required = true)
    @NotNull
	private String customerName;

    @ApiModelProperty(value = "Address to add", required = true)
    @NotNull
	private AddressTO address;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public AddressTO getAddress() {
		return address;
	}

	public void setAddress(AddressTO address) {
		this.address = address;
	}

}
