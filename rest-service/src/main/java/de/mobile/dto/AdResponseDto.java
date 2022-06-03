package de.mobile.dto;

import de.mobile.Category;
import lombok.Data;

import java.util.List;

@Data
public class AdResponseDto {

    private Long id;

    String make;

    List<CustomerDTO> customers;

    String model;

    private Category category;
}