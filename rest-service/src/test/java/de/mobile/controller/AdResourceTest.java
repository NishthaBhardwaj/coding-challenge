package de.mobile.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.mobile.TestUtil;
import de.mobile.domain.Category;
import de.mobile.domain.MobileAd;
import de.mobile.domain.MobileCustomer;
import de.mobile.dto.AdRequestDto;
import de.mobile.dto.AdResponseDto;
import de.mobile.dto.CustomerDTO;
import de.mobile.service.MobileAdService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AdResource.class)
class AdResourceTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MobileAdService service;



    public void testGetAd() throws Exception{
        //given
        MobileCustomer customer = new MobileCustomer("Jone",
                "Ream","JPMC","jone@gmail.com");
        customer.setId(200L);
        List<MobileAd> mobileAds = new ArrayList();/*List.of(new MobileAd(100L, customer, "Honda Jazz", "2350", "Honda Jazz Ad",
                        Category.Car, new BigDecimal(1020.4)),
                new MobileAd(100L, customer, "Honda City", "23511", "Honda City Ad",
                        Category.Car, new BigDecimal(1520.67)));*/

        MobileAd ad1 = new MobileAd(customer, "Honda Jazz", "2350", "Honda Jazz Ad",
                Category.Car, new BigDecimal(1020.4));
        MobileAd ad2 = new MobileAd(customer, "Honda City", "23511", "Honda City Ad",
                Category.Car, new BigDecimal(1520.67));
        ad1.setId(100L);
        ad2.setId(200L);

        AdResponseDto adWithCustomerDto = TestUtil.modelMapper().map(mobileAds.get(0), AdResponseDto.class);
        when(service.retrieveMobileAdById(anyLong())).thenReturn(adWithCustomerDto);
        String url = "/ad/100";
        String expectedJson = this.mapToJson(adWithCustomerDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andReturn();

        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(expectedJson);
    }

    @Test
    public void testCreateAd() throws Exception{
        //given

        AdRequestDto ad = new AdRequestDto();
        ad.setCategory(Category.Motorbike.toString());
        ad.setMake("Hero");
        ad.setPrice(new BigDecimal("500.58"));
        Long[] customersID= {10001L};
        ad.setCustomerId(customersID);
        ad.setModel("A1234");
        String adJsonInput = this.mapToJson(ad);
        String uri = "/ad/";

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(10001L);
        customerDTO.setFirstName("Ketty");
        customerDTO.setLastName("Julie");
        customerDTO.setEmail("Ketty@gmail.com");

        var adWithCustomerDetial = TestUtil.modelMapper().map(ad, AdResponseDto.class);
        adWithCustomerDetial.setCustomers(List.of(customerDTO));
        adWithCustomerDetial.setId(20001L);

        //when
        when(service.insertMobileAd(Mockito.any(AdRequestDto.class))).thenReturn(adWithCustomerDetial);
        RequestBuilder requestBuilder= MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON).content(adJsonInput)
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        //then
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String location = response.getHeader("Location");

        assertThat(location).contains("/ad/20001");
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }

    //maps an object into a JSON string. Uses a Jackson ObjectMapper
    private String mapToJson(Object obj) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);

    }
}