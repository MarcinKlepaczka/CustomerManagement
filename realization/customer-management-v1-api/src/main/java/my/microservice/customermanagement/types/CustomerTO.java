package my.microservice.customermanagement.types;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Customer class, contains all the information about particular customer")
public class CustomerTO {

    @ApiModelProperty(value = "Name/identifier of the customer", required = true)
    @NotNull
    @Size(max = 255, message = "Too long customer name.")
	private String name;

    @ApiModelProperty(value = "E-mail of the customer", required = false)
    @Email(message = "Invalid e-mail address.")
	private String email;

    @ApiModelProperty(value = "Customer's phone number", required = false)
    @Pattern(regexp="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message="Invalid phone number.")
    private String phone;

    @ApiModelProperty(value = "List of the assigned addresses", required = false)
	private List<AddressTO> addresses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<AddressTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressTO> addresses) {
		this.addresses = addresses;
	}



}
