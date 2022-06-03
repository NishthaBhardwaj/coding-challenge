package de.mobile.repository;

import java.util.ArrayList;
import java.util.List;

import de.mobile.domain.MobileAd;
import org.springframework.stereotype.Repository;


@Repository
public class AdRepository {

    public Long create(MobileAd ad) {
        throw new RuntimeException("not implemented yet");
    }

    public MobileAd get(Long adId) {
        throw new RuntimeException("not implemented yet");
    }

    public List<MobileAd> list() {
        return new ArrayList<>();
    }

}
