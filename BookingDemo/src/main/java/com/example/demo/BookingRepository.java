package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<FlightBooking, Long> {
	
	List<FlightBooking> findByPriceLessThan(double price);

}
