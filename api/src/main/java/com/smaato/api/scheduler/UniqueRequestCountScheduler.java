package com.smaato.api.scheduler;

import com.smaato.api.dto.ApiRequests;
import com.smaato.api.producer.KafkaProducer;
import com.smaato.api.service.ApiIds;
import com.smaato.api.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapReactive;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "enable", value = "schedule", havingValue = "true")
public class UniqueRequestCountScheduler {

    private final ApiIds apids;
    private final KafkaProducer producer;

    public UniqueRequestCountScheduler(ApiIds apids, KafkaProducer producer) {
        this.apids = apids;
        this.producer = producer;
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateCount() {
        String minute = RequestUtil.getPreviousMinute();
        RMapReactive<String, ApiRequests> cache = this.apids.getRequestCountMapCache();

        cache.get(minute).subscribe(cout -> {
            List<Integer> requests = cout.getRequests();
            Set<Integer> uniqueRequestCount = cout.getUniqueRequests();
            uniqueRequestCount.addAll(requests);
            cout.setUniqueRequests(uniqueRequestCount);
            cout.setCount(uniqueRequestCount.size());
            log.info("writing request count [{}] to kafka at {}", cout, minute);
            producer.send(minute + " " + uniqueRequestCount.size());
            Mono<Boolean> booleanMono = cache.fastPut(minute, cout);
            booleanMono.subscribe(bol -> log.info("Result of map put{}", bol));

        });

    }

}