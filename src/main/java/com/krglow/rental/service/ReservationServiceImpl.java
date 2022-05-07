package com.krglow.rental.service;

import com.krglow.rental.entity.Facility;
import com.krglow.rental.exception.DateConflictException;
import com.krglow.rental.exception.NotFoundException;
import com.krglow.rental.gui.model.ReservationAdd;
import com.krglow.rental.entity.Reservation;
import com.krglow.rental.repository.FacilityRepository;
import com.krglow.rental.repository.LandlordRepository;
import com.krglow.rental.repository.ReservationRepository;
import com.krglow.rental.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    private static final int PAGE_SIZE = 20;

    @Override
    @Transactional
    public Reservation create(ReservationAdd reservation) {

        if(isFacilityRent(facilityRepository.findById(reservation.getId_facility()).orElseThrow(() -> new NotFoundException()), reservation.getDateFrom(), reservation.getDateTo())){
            throw new DateConflictException();
        }

        Reservation entity = new Reservation();

        entity.setLandlord(landlordRepository.findById(reservation.getId_landlord()).orElseThrow(() -> new NotFoundException()));
        entity.setTenant(tenantRepository.findById(reservation.getId_tenant()).orElseThrow(() -> new NotFoundException()));
        entity.setFacility(facilityRepository.findById(reservation.getId_facility()).orElseThrow(() -> new NotFoundException()));
        entity.setDateFrom(reservation.getDateFrom());
        entity.setDateTo(reservation.getDateTo());

        long numOfDays = ChronoUnit.DAYS.between(reservation.getDateFrom(), reservation.getDateTo());
        BigDecimal cost = new BigDecimal(numOfDays).multiply(facilityRepository.findById(reservation.getId_facility()).get().getPrice());
        entity.setCost(cost);

        reservationRepository.save(entity);

    return entity;
    }

    @Override
    @Transactional
    public Reservation update(long id, ReservationAdd reservation){

        Reservation entity = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException()) ;


        if(isFacilityRent(facilityRepository.findById(reservation.getId_facility()).orElseThrow(() -> new NotFoundException()), reservation.getDateFrom(), reservation.getDateTo())){
            throw new DateConflictException();
        }

        entity.setLandlord(landlordRepository.findById(reservation.getId_landlord()).orElseThrow(() -> new NotFoundException()));
        entity.setTenant(tenantRepository.findById(reservation.getId_tenant()).orElseThrow(() -> new NotFoundException()));
        entity.setFacility(facilityRepository.findById(reservation.getId_facility()).orElseThrow(() -> new NotFoundException()));
        entity.setDateFrom(reservation.getDateFrom());
        entity.setDateTo(reservation.getDateTo());

        long numOfDays = ChronoUnit.DAYS.between(reservation.getDateFrom(), reservation.getDateTo());
        BigDecimal cost = new BigDecimal(numOfDays).multiply(facilityRepository.findById(reservation.getId_facility()).get().getPrice());
        entity.setCost(cost);

        reservationRepository.save(entity);

     return entity;

    }

    @Override
    public List<Reservation> getTenantReservations(String name, int page, Sort.Direction sort) {
        return reservationRepository.findAllByTenantName(name,
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    @Override
    public List<Reservation> getFacilityReservations(long facilityId, int page, Sort.Direction sort) {
        return reservationRepository.findAllByFacilityId(facilityId,
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    private boolean isFacilityRent(Facility facility, LocalDate from, LocalDate to){
        List<Reservation> rentFacilities = reservationRepository.getReservationsInPeriod(from, to);
        for (Reservation item : rentFacilities) {
            if (item.getFacility().getId() == facility.getId()) {
                return true;
            }
        }

        return false;
    }
}
