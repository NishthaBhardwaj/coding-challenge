package de.mobile.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CustomerDTO {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

}