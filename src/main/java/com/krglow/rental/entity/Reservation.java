package com.krglow.rental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_landlord")
    private Landlord landlord;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_tenant")
    private Tenant tenant;

    private BigDecimal cost;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_facility")
    private Facility facility;
}
