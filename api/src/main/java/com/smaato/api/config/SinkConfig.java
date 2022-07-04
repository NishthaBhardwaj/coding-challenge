package com.smaato.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfig {

    @Bean
    public Sinks.Many<String> sink(){
        return Sinks.many().replay().limit(1);
    }

    @Bean
    public Flux<String>  countBroadCast(Sinks.Many<String> sink){
        return sink.asFlux();

    }
}
