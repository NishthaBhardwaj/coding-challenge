package de.mobile.service;

import de.mobile.dto.AdResponseDto;
import de.mobile.dto.AdRequestDto;

import java.util.List;


public interface MobileAdService {
    AdResponseDto insertMobileAd(AdRequestDto mobileAd);
    void deleteMobileAdById(Long mobileAdId);
    AdResponseDto retrieveMobileAdById(Long mobileAdId);
    List<AdResponseDto> retrieveAllMobileAd();
}
