package com.smaato.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter @ToString
public class ApiRequests {

    private List<Integer>  requests = new ArrayList<>();
    private Set<Integer> uniqueRequests = new HashSet<>();
    private int count;
}
