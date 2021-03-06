package de.mobile.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@ToString
public class CustomerResponseDto {


    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private List<AdDTO> ads;

    private String companyName;
}
