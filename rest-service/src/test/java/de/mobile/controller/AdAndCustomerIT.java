package de.mobile.controller;

import de.mobile.Category;
import de.mobile.dto.AdRequestDto;
import de.mobile.dto.CustomerRequestDto;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdAndCustomerIT {

    @LocalServerPort
    Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAdIT() throws JSONException {
        String url = "/ad/3001";

        String response = this.restTemplate.getForObject(url, String.class);
        JSONAssert.assertEquals(
                "{id:3001,customers:[{id:2001,firstName:Nishtha,email:nishtha@gmail.com}]}"
                , response, false);
    }

    @Test
    public void createAdIT() {
        AdRequestDto ad = new AdRequestDto();
        Long[] customersID= {2001L};
        ad.setCustomerId(customersID);
        ad.setCategory(Category.Motorbike.toString());
        ad.setMake("Volvo");
        ad.setModel("4009E");

        String url = "http://localhost:" + port + "/ad";
        ResponseEntity<AdRequestDto> response =
                restTemplate.postForEntity(url, ad, AdRequestDto.class);

        String location = response.getHeaders().get("Location").get(0);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(location).contains(url);

    }

    @Test
    public void createCustomer_andGetCreatedCustomerIT() {
        CustomerRequestDto customer = new CustomerRequestDto();
        customer.setFirstName("Mark");
        customer.setLastName("Den");
        customer.setEmail("mark@gmail.com");

        String url = "http://localhost:" + port + "/customer";

        ResponseEntity<String> postResponse
                = restTemplate.postForEntity(url, customer, String.class);

        assertThat(postResponse.getHeaders().containsKey("Location")).isTrue();
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String resourceLocation = postResponse.getHeaders().get("Location").get(0);

        ResponseEntity<CustomerRequestDto> getResponse
                = restTemplate.getForEntity(resourceLocation, CustomerRequestDto.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();

    }
}