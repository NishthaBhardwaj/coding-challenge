package de.mobile.dto;

import de.mobile.domain.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AdResponseDto {

    private Long id;

    String make;

    List<CustomerDTO> customers;

    String model;

    private Category category;

    private String description;

    private BigDecimal price;
}