package com.smaato.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class ReportDto {


    private String time;
    private int totalRequests;
    private int totalUniqueRequests;
}
