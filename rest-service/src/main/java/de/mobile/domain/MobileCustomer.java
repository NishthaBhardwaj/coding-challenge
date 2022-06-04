package de.mobile.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MobileCustomer {

    @Id
    @GeneratedValue
    private Long id;

    /*@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<MobileAd> ads= new ArrayList<>();
*/
    @ManyToMany(mappedBy = "customers" , cascade = CascadeType.DETACH)
    private List<MobileAd> ads= new ArrayList<>();

    private String firstName;

    private String lastName;

    private String companyName;

    private String email;

    @Builder
    public MobileCustomer(String firstName, String lastName, String companyName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.email = email;
    }



}