package de.mobile.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.mobile.domain.Category;
import de.mobile.validation.Enum;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@ToString
public class AdRequestDto {

    @JsonIgnore
    private Long id;

    @NotEmpty(message = "Make is required")
    @Size(min = 2, message = "Make should be at lest 2 characters")
    private String make;

    @NotNull(message = "CustomerId is required")
    private Long[] customerId;

    @NotEmpty(message = "Model is required")
    private String model;

    @Enum(enumClass= Category.class ,message = "Category is not valid")
    private String category;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin("1.00")
    private BigDecimal price;

}