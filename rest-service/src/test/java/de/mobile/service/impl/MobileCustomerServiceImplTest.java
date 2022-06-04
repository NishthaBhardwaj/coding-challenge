package de.mobile.service.impl;

import de.mobile.TestUtil;
import de.mobile.domain.MobileCustomer;
import de.mobile.dto.CustomerRequestDto;
import de.mobile.dto.CustomerResponseDto;
import de.mobile.exception.ResourceNotFoundException;
import de.mobile.repository.MobileCutomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MobileCustomerServiceImplTest {


    @InjectMocks
    MobileCustomerServiceImpl customerService;

    @Mock
    MobileCutomerRepository customerRepository;

    @Mock
    ModelMapper modelMapp;

    static CustomerRequestDto customerRequestDto = new CustomerRequestDto();
    static MobileCustomer mobileCustomer =new MobileCustomer();
    static CustomerResponseDto customerResponseDto = new CustomerResponseDto();

    @BeforeAll
    static void setup() {

        customerRequestDto.setEmail("Betty@gmail.com");
        customerRequestDto.setFirstName("Betty");
        customerRequestDto.setLastName("Ben2");
        mobileCustomer = TestUtil.modelMapper().map(customerRequestDto, MobileCustomer.class);
        mobileCustomer.setId(1000L);
        customerResponseDto = TestUtil.modelMapper().map(mobileCustomer, CustomerResponseDto.class);

    }


    @Test
    void testInsertMobileCustomer() {
         given(modelMapp.map(any(), any())).willReturn(mobileCustomer).willReturn(customerResponseDto);
         when(customerRepository.save(any(MobileCustomer.class))).thenReturn(mobileCustomer);
         assertEquals("Betty",customerService.insertMobileCustomer(customerRequestDto).getFirstName());


    }

    @Test
    void testDeleteCustomerAdById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(mobileCustomer));
        customerService.deleteMobileCustomerById(mobileCustomer.getId());
        verify(customerRepository).delete(mobileCustomer);
    }

    @Test
    void testDeleteCustomerAdById_throwsExceptionIfIDNotFound() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> customerService.deleteMobileCustomerById(10000L))
                .withMessage("Customer not found with id : " + 10000L);
    }

}