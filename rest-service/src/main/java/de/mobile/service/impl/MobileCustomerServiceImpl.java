package de.mobile.service.impl;

import de.mobile.domain.MobileCustomer;
import de.mobile.dto.CustomerRequestDto;
import de.mobile.dto.CustomerResponseDto;
import de.mobile.exception.ResourceNotFoundException;
import de.mobile.repository.MobileCutomerRepository;
import de.mobile.service.MobileCustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MobileCustomerServiceImpl implements MobileCustomerService {

    private final MobileCutomerRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerRequestDto insertMobileCustomer(CustomerRequestDto customerDto) {
        return modelMapper.map(
        repository.save(modelMapper.map(customerDto,MobileCustomer.class)), CustomerRequestDto.class);

    }

    @Override
    public void deleteMobileCustomerById(Long customerId) {
        repository.deleteById(customerId);

    }

    @Override
    public CustomerResponseDto retrieveMobileCustomerById(Long customerId) {
        var customer = repository.findById(customerId);
        MobileCustomer mobileCustomer = customer
                .orElseThrow(() -> {
            throw new ResourceNotFoundException("Customer", "id : " + customerId);
        });
        log.debug("Customer -> {} {} ",mobileCustomer,mobileCustomer.getAds());
        return modelMapper.map(mobileCustomer, CustomerResponseDto.class);
    }

    @Override
    public List<CustomerResponseDto> retrieveAllMobileCustomers() {

        var customers =  repository.findAll();
        if(customers.isEmpty())
            throw new ResourceNotFoundException("Customers","");
        return customers
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
                .collect(Collectors.toList());

    }
}