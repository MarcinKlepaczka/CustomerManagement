package my.microservice.customermanagement.types;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Address of the customer")
public class AddressTO {

    @ApiModelProperty(value = "Name of the street", required = false)
    @Size(max = 255, message = "Too long street name.")
	private String street;

    @ApiModelProperty(value = "Postal code", required = false)
    @Size(max = 255, message = "Too long zip code.")
	private String zipCode;

    @ApiModelProperty(value = "Number of the house", required = false)
    @Size(max = 255, message = "Too long house number.")
	private String houseNumber;

    @ApiModelProperty(value = "Name of the city", required = false)
    @Size(max = 255, message = "Too long city name.")
	private String city;

    @ApiModelProperty(value = "Customer's country", required = true)
    @Size(max = 255, message = "Too long country name.")
    @NotNull
	private String country;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
