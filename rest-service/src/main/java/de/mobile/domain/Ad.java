package de.mobile.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;


@Data
@ToString
public class Ad {

    private Long id;

    private String make;

    private String model;

    private String description;

    private Category category;

    private BigDecimal price;

}