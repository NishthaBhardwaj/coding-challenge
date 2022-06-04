package de.mobile.service.impl;

import de.mobile.TestUtil;
import de.mobile.domain.Category;
import de.mobile.domain.MobileAd;
import de.mobile.domain.MobileCustomer;
import de.mobile.dto.AdRequestDto;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileAdServiceImplTest {

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
    static AdRequestDto adRequestDto = new AdRequestDto();
    static AdResponseDto adResponseDto;

    @BeforeAll
    static void setup() {

        customer = new MobileCustomer("Jone",
                "Ream", "JPMC", "jone@gmail.com");
        customer.setId(200L);

        MobileAd ad1 = new MobileAd(customer, "Honda Jazz", "2350", "Honda Jazz Ad",
                Category.Car, new BigDecimal("1020.4"));
        MobileAd ad2 = new MobileAd(customer, "Honda City", "23511", "Honda City Ad",
                Category.Car, new BigDecimal("1520.67"));
        ad1.setId(100L);
        ad2.setId(200L);

        Long[] customerIds = {200L};
        adRequestDto.setCustomerId(customerIds);
        adRequestDto.setCategory(Category.Car.toString());
        adRequestDto.setMake("Honda");
        adRequestDto.setModel("5467");

        mobileAds = List.of(ad1, ad2);
        adResponseDto = TestUtil.modelMapper().map(mobileAds.get(0), AdResponseDto.class);

    }


    @Test
    void insertMobileAd() {
        //given
        MobileAd mobileAd = TestUtil.modelMapper().map(adRequestDto, MobileAd.class);
        mobileAd.setId(1000L);

        AdResponseDto adResponseDto = TestUtil.modelMapper().map(mobileAd, AdResponseDto.class);
        given(modelMapp.map(any(), any())).willReturn(mobileAd).willReturn(adResponseDto);

        //when
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(mobileAdRepository.save(any(MobileAd.class))).thenReturn(mobileAd);

        //then
        assertEquals(mobileAd.getId(), mobileAdService.insertMobileAd(adRequestDto).getId());

    }

    @Test
    void testInsertMobileAd_whenThrowsException() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> mobileAdService.insertMobileAd(adRequestDto))
                .withMessage("Customer not found with Id : " + adRequestDto.getCustomerId()[0]);
    }

    @Test
    void testRetrieveMobileAdById() {
        //given
        MobileAd mobileAd = mobileAds.get(0);

        //when
        when(mobileAdRepository.findById(anyLong()))
                .thenReturn(Optional.of(mobileAd));
        when(modelMapp.map(any(), any())).thenReturn(adResponseDto);
        var adWithCutDto = mobileAdService.retrieveMobileAdById(100L);

        //then
        assertEquals(Long.valueOf(100), adWithCutDto.getId());
        assertNotNull(adWithCutDto.getCustomers());

    }

    @Test
    void testRetrieveMobileAdById_whenThrowsException() {
        when(mobileAdRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> mobileAdService.retrieveMobileAdById(10000L));
    }


    @Test
    void testDeleteMobileAdById() {
        when(mobileAdRepository.findById(anyLong())).thenReturn(Optional.of(mobileAds.get(0)));
        mobileAdService.deleteMobileAdById(mobileAds.get(0).getId());
        verify(mobileAdRepository).delete(mobileAds.get(0));
    }

    @Test
    void testDeleteMobileAdById_throwsExceptionIfIDNotFound() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> mobileAdService.deleteMobileAdById(mobileAds.get(0).getId()))
                .withMessage("Ad not found with id : " + mobileAds.get(0).getId());
    }

}