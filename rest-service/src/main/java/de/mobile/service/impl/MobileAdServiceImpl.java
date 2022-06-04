package de.mobile.service.impl;

import de.mobile.domain.MobileAd;
import de.mobile.domain.MobileCustomer;
import de.mobile.dto.AdResponseDto;
import de.mobile.dto.AdRequestDto;
import de.mobile.exception.ResourceNotFoundException;
import de.mobile.repository.MobileAdRepository;
import de.mobile.repository.MobileCutomerRepository;
import de.mobile.service.MobileAdService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MobileAdServiceImpl implements MobileAdService {


    private MobileAdRepository repository;
    private ModelMapper modelMapper;
    private final MobileCutomerRepository customerRepository;

    @Override
    @Transactional
    public AdResponseDto insertMobileAd(AdRequestDto adDto) {
        log.debug("incoming data -> {} ", adDto);

        List<MobileCustomer> customersList = getMobileCustomers(adDto);
        MobileAd mobileAd = modelMapper.map(adDto, MobileAd.class);

        mobileAd.setCustomers(customersList);
        MobileAd entity = repository.save(mobileAd);
        return modelMapper.map(entity, AdResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteMobileAdById(Long mobileAdId) {
        MobileAd ad = repository.findById(mobileAdId)
                .orElseThrow(() -> new ResourceNotFoundException("Ad" ,"id : " + mobileAdId));
        repository.delete(ad);
    }


    @Override
    public AdResponseDto retrieveMobileAdById(Long mobileAdId) {
        MobileAd entity = repository.findById(mobileAdId)
                .orElseThrow(() -> new ResourceNotFoundException("Ad" ,"id : " + mobileAdId));
        log.info("Ad -> {} Customer : {} ",entity,entity.getCustomers());
        return modelMapper.map(entity, AdResponseDto.class);
    }

    @Override
    public List<AdResponseDto> retrieveAllMobileAd() {
        List<MobileAd> ads = repository.findAll();

        if(!ads.isEmpty()){
            List<AdResponseDto> adDtoList = new ArrayList<>();
            ads.forEach(adEntity -> adDtoList.add(modelMapper.map(adEntity, AdResponseDto.class)));
            return adDtoList;
        }else{
            throw new ResourceNotFoundException("Ads" ,"");
        }
    }

    private List<MobileCustomer> getMobileCustomers(AdRequestDto adDto) {
        Long[] customerId = adDto.getCustomerId();
        List<MobileCustomer> customersList = new ArrayList<>();
        for(Long custID: customerId){
            MobileCustomer mobileCustomer = customerRepository.findById(custID)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id : " + custID));
            customersList.add(mobileCustomer);
        }
        log.debug("Customers {}", customersList);
        return customersList;
    }

}