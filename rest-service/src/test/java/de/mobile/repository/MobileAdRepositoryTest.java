package de.mobile.repository;

import de.mobile.domain.MobileAd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MobileAdRepositoryTest {

    @Autowired
    private MobileAdRepository repository;

    @Test
    public void testFindAll(){
        List<MobileAd> ads = repository.findAll();
        assertThat(ads).isNotEmpty();

    }

}