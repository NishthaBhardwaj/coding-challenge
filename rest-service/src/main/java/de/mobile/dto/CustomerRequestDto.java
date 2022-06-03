package de.mobile.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ToString
public class CustomerRequestDto {

    @JsonIgnore
    private Long id;

    @NotEmpty(message = "FirstName is required")
    @Size(min = 2, message = "FirstName should have at least 2 characters")
    @Pattern(regexp = "^[A-Za-z]+$",message = "FirstName is not valid")
    private String firstName;

    @NotEmpty(message = "LastName is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message = "LastName is not valid")
    private String lastName;

    @NotEmpty
    @Email
    private String email;

}