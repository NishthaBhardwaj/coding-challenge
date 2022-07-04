package com.smaato.api.controller;

import com.smaato.api.client.RequestCountClient;
import com.smaato.api.dto.ApiRequests;
import com.smaato.api.dto.ReportDto;
import com.smaato.api.service.ApiIds;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("ids")
@Slf4j
public class IdsController {

    private final ApiIds apiIds;
    private final RequestCountClient client;

    public IdsController(ApiIds apiIds, RequestCountClient client) {
        this.apiIds = apiIds;
        this.client = client;
    }

    //endpoint=http://localhost:8081/external/api/count

    @GetMapping("/accept")
    public ResponseEntity<String> accept(@RequestParam(required = true) int id,
                                         @RequestParam(required = false) String endpoint) {
        Mono<ApiRequests> monoMono = apiIds.addRequest(id);
        monoMono.subscribe(count -> log.debug("Request Count {}", count.getCount()));
        if (endpoint != null) {
            this.getCount().subscribe(count -> {
                client.postRequestCount(endpoint, count).subscribe(status -> {
                    log.info("status {}", status);

                });
            });
        }

        return ResponseEntity.status(HttpStatus.OK).build();

    }


    public Mono<Integer> getCount() {
        System.out.println("count");
        return apiIds.getCurrentCount().map(requestCount -> {
            Set<Integer> uniqueRequestCount = requestCount.getUniqueRequests();

            if (uniqueRequestCount.size() == 0) {
                uniqueRequestCount.addAll(requestCount.getRequests());
                return uniqueRequestCount.size();

            } else {
                return uniqueRequestCount.size();
            }
        });
    }

    @GetMapping("report")
    public Flux<ReportDto> getRequestsReport(){
        ReportDto reportDto = new ReportDto();
        return this.apiIds.getAllCacheData();
    }


}