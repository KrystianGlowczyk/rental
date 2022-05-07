package com.krglow.rental.repository;

import com.krglow.rental.entity.Landlord;
import com.krglow.rental.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Long> {
}
