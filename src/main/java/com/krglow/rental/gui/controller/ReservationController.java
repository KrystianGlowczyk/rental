package com.krglow.rental.gui.controller;

import com.krglow.rental.gui.model.ReservationAdd;
import com.krglow.rental.entity.Reservation;
import com.krglow.rental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody ReservationAdd reservation){
        return new ResponseEntity<>(service.create(reservation), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Reservation>update(@PathVariable("id") long id, @RequestBody ReservationAdd reservation){
        return new ResponseEntity<>(service.update(id, reservation), HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = ""+ "/tenant")
    public ResponseEntity<List<Reservation>> getReservationsByTenant(@RequestParam String name, @RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page >= 0 ? page - 1 : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return new ResponseEntity<>(service.getTenantReservations(name, pageNumber, sortDirection), HttpStatus.OK);
    }

    @GetMapping(value = ""+ "/facility")
    public ResponseEntity<List<Reservation>> getReservationsByFacility(@RequestParam long id, @RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page >= 0 ? page - 1  : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return new ResponseEntity<>(service.getFacilityReservations(id, pageNumber, sortDirection), HttpStatus.OK);
    }
}
