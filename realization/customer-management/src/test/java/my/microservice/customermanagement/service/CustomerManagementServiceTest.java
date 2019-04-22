package my.microservice.customermanagement.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import my.microservice.customermanagement.core.CustomerManagement;
import my.microservice.customermanagement.types.CustomerTO;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
@Import(CustomerManagementRestService.class)
public class CustomerManagementServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerManagement customerManagement;

    @Test
    public void addCustomerCorrectRequest() throws Exception {
    	CustomerTO customerTO = new CustomerTO();
    	customerTO.setName("Customer");
    	customerTO.setEmail("xx@xx.pl");

    	when(customerManagement.addCustomer(any())).thenReturn(customerTO);

        MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"));
        String customer = "{\"name\": \"Customer\", \"email\" : \"xx@xx.pl\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/api/addCustomer")
          .content(customer)
          .header("Authorization", "Basic YWRtaW46YWRtaW4=")
          .contentType(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content()
        		  .json(customer))
          .andExpect(MockMvcResultMatchers.content()
        		  .contentType(mediaType));
    }

    @Test
    public void addCustomerInvalidRequest() throws Exception {

      String customer = "{\"email\" : \"xx@xx.pl\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/api/addCustomer")
          .content(customer)
          .header("Authorization", "Basic YWRtaW46YWRtaW4=")
          .contentType(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addCustomerNoAuth() throws Exception {

      String customer = "{\"email\" : \"xx@xx.pl\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/api/addCustomer")
          .content(customer)
          .contentType(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    //TODO Test more endpoints!

}
