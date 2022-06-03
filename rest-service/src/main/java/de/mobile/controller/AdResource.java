package de.mobile.controller;

import de.mobile.dto.AdResponseDto;
import de.mobile.dto.AdRequestDto;
import de.mobile.service.MobileAdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ad")
@Slf4j
public class AdResource implements ControllerService {

    private final MobileAdService adService;

    @GetMapping("/{id}")
    public AdResponseDto getAd(@NotNull @PathVariable("id") String adId) {
        return adService.retrieveMobileAdById(Long.valueOf(adId));
    }

    @PostMapping
    public ResponseEntity<String> createAd(@Valid @RequestBody AdRequestDto ad) {
        log.info("Incoming Ad data {} ", ad);
        var customer = adService.insertMobileAd(ad);
        String path = "/ad/{id}";
        return createLocation(path, String.valueOf(customer.getId()));
    }

    @GetMapping
    public List<AdResponseDto> getAllAd() {
        return adService.retrieveAllMobileAd();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String adId){
        adService.deleteMobileAdById(Long.valueOf(adId));
        return new ResponseEntity(HttpStatus.OK);
    }
}