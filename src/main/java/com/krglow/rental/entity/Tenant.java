package com.krglow.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tenant extends Person {

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenant")
//    private Set<Reservation> reservations;
}
