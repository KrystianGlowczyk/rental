package com.krglow.rental.service;

import com.krglow.rental.gui.model.ReservationAdd;
import com.krglow.rental.entity.Reservation;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ReservationService {

    Reservation create(ReservationAdd reservation);

    Reservation update(long id, ReservationAdd reservation);

    List<Reservation> getTenantReservations(String name, int page, Sort.Direction sort);

    List<Reservation> getFacilityReservations(long facilityId, int page, Sort.Direction sort);
}
