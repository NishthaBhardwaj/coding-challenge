package de.mobile.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.mobile.domain.Ad;
import de.mobile.domain.MobileAd;
import de.mobile.repository.AdRepository;
import org.springframework.stereotype.Service;


@Service
public class AdService {

    private final AdRepository adRepository;

    @Inject
    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public Long create(Ad adData) {
        MobileAd ad = inbound(adData);
        return adRepository.create(ad);
    }

    public Ad get(Long adId) {
        MobileAd mobileAd = adRepository.get(adId);
        return outbound(mobileAd);
    }

    public List<Ad> list() {
        return adRepository.list() //
                .stream() //
                .map(this::outbound) //
                .collect(Collectors.toList());
    }

    private Ad outbound(MobileAd mobileAd) {
        Ad adData = new Ad();
        adData.setId(mobileAd.getId());
        adData.setCategory(mobileAd.getCategory());
        adData.setMake(mobileAd.getMake());
        adData.setModel(mobileAd.getModel());
        adData.setDescription(mobileAd.getDescription());
        adData.setPrice(mobileAd.getPrice());
        return adData;
    }

    private MobileAd inbound(Ad adData) {
        MobileAd mobileAd = new MobileAd();
        mobileAd.setId(adData.getId());
        mobileAd.setCategory(adData.getCategory());
        mobileAd.setMake(adData.getMake());
        mobileAd.setModel(adData.getModel());
        mobileAd.setDescription(adData.getDescription());
        mobileAd.setPrice(adData.getPrice());
        return mobileAd;
    }

}
