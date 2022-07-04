package com.smaato.api.service;

import com.smaato.api.dto.ApiRequests;
import com.smaato.api.dto.ReportDto;
import com.smaato.api.util.RequestUtil;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ApiIds {


    private final RMapReactive<String, ApiRequests> requestCountMapCache;

    public ApiIds(RedissonReactiveClient client) {
        this.requestCountMapCache = client.getMap("requestCount",
                new TypedJsonJacksonCodec(String.class, ApiRequests.class));
    }

    public Mono<ApiRequests> addRequest(int id) {
        String minute = RequestUtil.getCurrentTime();
        return this.requestCountMapCache.get(minute).doOnNext(count -> {
            List<Integer> requests = count.getRequests();
            requests.add(id);
            count.setRequests(requests);
            this.requestCountMapCache.fastPut(minute, count);
        }).switchIfEmpty(Mono.fromSupplier(() -> {
            ApiRequests requestCount = new ApiRequests();
            List<Integer> requests = requestCount.getRequests();
            requests.add(id);
            requestCount.setRequests(requests);
            return requestCount;
        })).flatMap(rc -> this.requestCountMapCache.fastPut(minute, rc).thenReturn(rc));

    }

    public Mono<ApiRequests> getCurrentCount() {
        String time = RequestUtil.getCurrentTime();
        return this.requestCountMapCache.get(time);

    }

    public RMapReactive<String, ApiRequests> getRequestCountMapCache() {
        return requestCountMapCache;
    }

    public Flux<ReportDto> getAllCacheData(){
        Flux<Map.Entry<String, ApiRequests>> entryFlux = this.requestCountMapCache.entryIterator();
        return entryFlux.map(map -> {
            ReportDto reportDto = new ReportDto();
            reportDto.setTime(map.getKey());
            reportDto.setTotalRequests(map.getValue().getRequests().size());
            reportDto.setTotalUniqueRequests(map.getValue().getUniqueRequests().size());
            return reportDto;

        });
    }
}