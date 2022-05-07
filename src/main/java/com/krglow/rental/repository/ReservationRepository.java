package com.krglow.rental.repository;

import com.krglow.rental.entity.Facility;
import com.krglow.rental.entity.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //@Query("SELECT p FROM Reservation  p WHERE p.tenant.name = ?1")
    List<Reservation> findAllByTenantName(String name, Pageable page);

    //@Query(value = "SELECT * FROM reservation WHERE id_facility = ?1", nativeQuery = true)
    List<Reservation> findAllByFacilityId(long id, Pageable page);

    @Query("SELECT o FROM Reservation o WHERE o.dateFrom >= ?1 AND o.dateTo <= ?2")
    List<Reservation> getReservationsInPeriod(LocalDate from, LocalDate to);

}
