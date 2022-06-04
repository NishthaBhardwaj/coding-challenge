package de.mobile.dto;

import de.mobile.domain.Category;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AdDTO {
    private Long id;

    private String make;

    private String model;

    private Category category;
}
