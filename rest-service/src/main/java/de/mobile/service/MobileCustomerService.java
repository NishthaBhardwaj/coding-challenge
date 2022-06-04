package de.mobile.service;

import de.mobile.dto.CustomerRequestDto;
import de.mobile.dto.CustomerResponseDto;

import java.util.List;

public interface MobileCustomerService {

    CustomerResponseDto insertMobileCustomer(CustomerRequestDto customerDto);

    void deleteMobileCustomerById(Long customerId);

    CustomerResponseDto retrieveMobileCustomerById(Long customerId);

    List<CustomerResponseDto> retrieveAllMobileCustomers();
}
