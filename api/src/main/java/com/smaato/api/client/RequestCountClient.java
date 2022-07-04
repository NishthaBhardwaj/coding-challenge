package com.smaato.api.client;

import com.smaato.api.dto.RequestCount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RequestCountClient {

    private final WebClient webClient;

    public RequestCountClient(@Value("${requestcount.service.url}") String url) {
        System.out.println("url" + url);
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<String> postRequestCount(String endpoint, int count) {
        RequestCount rCount = new RequestCount();
        rCount.setRequestsCount(count);
        return webClient.post().uri(endpoint)
                .body(Mono.just(rCount), RequestCount.class)
                .retrieve()
                .bodyToMono(String.class);
    }

}