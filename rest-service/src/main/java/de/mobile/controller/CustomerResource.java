package de.mobile.controller;

import de.mobile.dto.CustomerRequestDto;
import de.mobile.dto.CustomerResponseDto;
import de.mobile.service.MobileCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerResource implements ControllerService{

    private final MobileCustomerService mobileCustomerService;

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomer(@PathVariable("id") String custId) {

        return mobileCustomerService.retrieveMobileCustomerById(Long.parseLong(custId));
    }
    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequestDto customerDto){
        log.info("Incoming customer data {} ", customerDto);

        var customer = mobileCustomerService.insertMobileCustomer(customerDto);
        String resourcePath = "/customer/{id}";
        return createLocation(resourcePath,String.valueOf(customer.getId()));

    }

    @GetMapping
    public List<CustomerResponseDto> getAllCustomer() {
        return mobileCustomerService.retrieveAllMobileCustomers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String custId){
        mobileCustomerService.deleteMobileCustomerById(Long.valueOf(custId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}