package de.mobile.repository;

import de.mobile.domain.MobileAd;
import de.mobile.domain.MobileCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MobileCutomerRepository extends JpaRepository<MobileCustomer,Long> {

    @Modifying
    @Query(value = "DELETE FROM MOBILE_AD_CUSTOMER where customer_id = :cust_id", nativeQuery = true)
    public int deleteAdCustomerRelationShip(Long cust_id);

}
