package de.mobile.repository;

import de.mobile.domain.MobileAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileAdRepository extends JpaRepository<MobileAd,Long> {
}
