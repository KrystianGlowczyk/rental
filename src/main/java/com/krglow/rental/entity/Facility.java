package com.krglow.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(scale = 2)
    private BigDecimal price;

    private double area;

    private String description;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facility")
//    private Set<Reservation> reservations;
}
