package com.smaato.externalservice.controller;

import com.smaato.externalservice.dto.RequestCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external/api")
@Slf4j
public class ExternalController {


    @PostMapping("/count")
    public ResponseEntity<String> requestCount(@RequestBody RequestCount requestCount){
        log.info("Requests Count received {}", requestCount.getRequestsCount());
        return ResponseEntity.status(HttpStatus.CREATED).body("Resource Created");
    }
}
