package de.mobile.dto;

import de.mobile.Category;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import de.mobile.validation.Enum;

@Data
@ToString
public class AdRequestDto {


    @NotEmpty(message = "Make is required")
    @Size(min = 2, message = "Make should be at lest 2 characters")
    private String make;

    @NotNull(message = "CustomerId is required")
    private Long[] customerId;

    @NotEmpty(message = "Model is required")
    private String model;

    @Enum(enumClass=Category.class ,message = "Category is not valid")
    private String category;

}