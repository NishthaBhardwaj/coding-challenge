package de.mobile.service.impl;


import de.mobile.Category;
import de.mobile.TestUtil;
import de.mobile.domain.MobileAd;
import de.mobile.domain.MobileCustomer;
import de.mobile.dto.AdResponseDto;
import de.mobile.exception.ResourceNotFoundException;
import de.mobile.repository.MobileAdRepository;
import de.mobile.repository.MobileCutomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileAdServiceImplTest implements TestUtil {

    @InjectMocks
    MobileAdServiceImpl mobileAdService;

    @Mock
    MobileAdRepository mobileAdRepository;

    @Mock
    ModelMapper modelMapp;


    @Mock
    MobileCutomerRepository customerRepository;

    static MobileCustomer customer;
    static List<MobileAd> mobileAds;



    static AdResponseDto adWithCustomerDto;

    @BeforeAll
    static void setup(){
       customer = new MobileCustomer("Jone",
                "Ream","JPMC","jone@gmail.com");
        customer.setId(200L);

        MobileAd ad1 = new MobileAd(customer, "Honda Jazz", "2350", "Honda Jazz Ad",
                Category.Car, new BigDecimal(1020.4));
        MobileAd ad2 = new MobileAd(customer, "Honda City", "23511", "Honda City Ad",
                Category.Car, new BigDecimal(1520.67));
        ad1.setId(100L);
        ad2.setId(200L);


        mobileAds = List.of(ad1,ad2);

        adWithCustomerDto = TestUtil.modelMapper().map(mobileAds.get(0), AdResponseDto.class);

    }


    @Test
    void insertMobileAd(){
        when(mobileAdRepository.findAll()).thenReturn(mobileAds);
        when(modelMapp.map(any(),any())).thenReturn(adWithCustomerDto);
        assertEquals(2,mobileAdService.retrieveAllMobileAd().size());

    }
    @Test
    void insertMobileAd_whenthrowException(){
        when(mobileAdRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class,
                () -> mobileAdService.retrieveAllMobileAd());
    }

    @Test
    void retrieveMobileAdById(){
        //given
        MobileAd mobileAd = mobileAds.get(0);

        //when
        when(mobileAdRepository.findById(anyLong()))
                .thenReturn(Optional.of(mobileAd));
        when(modelMapp.map(any(),any())).thenReturn(adWithCustomerDto);
        var adWithCutDto = mobileAdService.retrieveMobileAdById(100l);

        //then
        assertEquals(Long.valueOf(100),adWithCutDto.getId());
        assertNotNull(adWithCutDto.getCustomers());


    }

    @Test
    void retrieveMobileAdById_whenThrowException(){
        when(mobileAdRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> mobileAdService.retrieveMobileAdById(Long.valueOf(10000)));
    }



}