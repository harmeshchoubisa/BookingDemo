package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

	private BookingRepository bookingRepository;
	
	@Autowired
	public BookingController(BookingRepository bookingRepository)
	{
		this.bookingRepository = bookingRepository;
		
	}
	
	@GetMapping(path = "/all")
	public List<FlightBooking> getAllBookings()
	{
		return bookingRepository.findAll();
	}
	
	@GetMapping(path = "/affordable/{price}")
	public List<FlightBooking> getAffordableBooking(@PathVariable double price)
	{
		return bookingRepository.findByPriceLessThan(price)	;
	}
	
	@PostMapping(path = "/create")
	public List<FlightBooking> createBookings(@RequestBody FlightBooking flightBooking )
	{
		bookingRepository.save(flightBooking);
		return bookingRepository.findAll();
	}
	
	@PostMapping(path = "/delete/{id}")
	public List<FlightBooking> deleteBookings(@PathVariable long id)
	{
		bookingRepository.deleteById(id);
		return bookingRepository.findAll();
	}
}
