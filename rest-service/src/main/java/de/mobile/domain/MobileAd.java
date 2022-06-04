package de.mobile.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class MobileAd {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="MOBILE_AD_CUSTOMER",
    joinColumns = @JoinColumn(name = "ad_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<MobileCustomer> customers= new ArrayList<>();

    private String make;

    private String model;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private BigDecimal price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<MobileCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(MobileCustomer customers) {
        this.customers.add(customers);
    }

    public void setCustomers(List<MobileCustomer> customers) {
        this.customers.addAll(customers);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MobileAd(MobileCustomer customer, String make, String model,
                    String description, Category category, BigDecimal price) {
        this.customers.add(customer);
        this.make = make;
        this.model = model;
        this.description = description;
        this.category = category;
        this.price = price;
    }
}
