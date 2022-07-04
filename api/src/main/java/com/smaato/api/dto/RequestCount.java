package com.smaato.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RequestCount {

    private Integer requestsCount;
    private String userId;
    private String secret;
}
