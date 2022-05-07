package com.krglow.rental.gui.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Rezerwacja")
public class ReservationAdd {

    @ApiModelProperty(value = "Identyfikator wynajmujacego")
    private long id_landlord;

    @ApiModelProperty(value = "Identyfikator najemcy")
    private long id_tenant;

    @ApiModelProperty(value = "Identyfikator obiektu")
    private long id_facility;

    @ApiModelProperty(value = "Data rozpoczecia rezerwacji")
    private LocalDate dateFrom;

    @ApiModelProperty(value = "Data zakonczenia rezerwacji")
    private LocalDate dateTo;

}
