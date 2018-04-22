package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<FlightBooking> getAllBookings()
	{
		return bookingRepository.findAll();
	}
	
	@RequestMapping(value = "/affordable/{price}", method = RequestMethod.GET)
	public List<FlightBooking> getAffordableBooking(@PathVariable double price)
	{
		return bookingRepository.findByPriceLessThan(price)	;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public List<FlightBooking> createBookings(@RequestBody FlightBooking flightBooking )
	{
		bookingRepository.save(flightBooking);
		return bookingRepository.findAll();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public List<FlightBooking> deleteBookings(@PathVariable long id)
	{
		bookingRepository.deleteById(id);
		return bookingRepository.findAll();
	}
}
