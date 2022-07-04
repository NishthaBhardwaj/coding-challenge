package com.smaato.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafKaConsumerService {

    @KafkaListener(topics = "request.count",
            groupId = "count")
    public void consume(String message) {
        log.info("Message received from kafka -> {}", message);

    }

}