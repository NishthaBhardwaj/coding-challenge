package de.mobile.repository;

import de.mobile.domain.MobileAd;
import de.mobile.domain.MobileCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileCutomerRepository extends JpaRepository<MobileCustomer,Long> {
}
