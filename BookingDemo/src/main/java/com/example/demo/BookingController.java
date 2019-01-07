package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.FlightNotFoundException;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BookingController.class);

	private BookingRepository bookingRepository;
	
	@Autowired
	public BookingController(BookingRepository bookingRepository)
	{
		this.bookingRepository = bookingRepository;
		
	}
	
	@GetMapping(path = "/all")
	public List<FlightBooking> getAllBookings()
	{
		LOGGER.info("getAllBookings- {}", "getAllBookings start function");          
		LOGGER.info("getAllBookings- {}", "getAllBookings end function");
		return bookingRepository.findAll();
	}
	
	@GetMapping(path = "/affordable/{price}")
	public List<FlightBooking> getAffordableBooking(@Valid @PathVariable double price)
	{
		LOGGER.info("getAffordableBooking- {}", "getAffordableBooking start function");          
		LOGGER.info("getAffordableBooking- {}", "getAffordableBooking end function");
		return bookingRepository.findByPriceLessThan(price)	;
	}
	
	@PostMapping(path = "/create")
	public List<FlightBooking> createBookings(@Valid @RequestBody FlightBooking flightBooking )
	{
		LOGGER.info("createBookings- {}", "createBookings start function");
		bookingRepository.save(flightBooking);
		LOGGER.info("createBookings- {}", "createBookings end function");
		return bookingRepository.findAll();
	}
	
	@PostMapping(path = "/delete/{id}")
	public List<FlightBooking> deleteBookings(@Valid @PathVariable long id)
	{
		LOGGER.info("deleteBookings- {}", "deleteBookings start function");
		Optional<FlightBooking> flightBooking = bookingRepository.findById(id);
		if(!flightBooking.isPresent())
		{
			LOGGER.error("error scenario- {}", "No booking present to delete");
			throw new FlightNotFoundException("No booking present");
		}
		bookingRepository.deleteById(id);
		LOGGER.info("deleteBookings- {}", "deleteBookings end function");
		return bookingRepository.findAll();
	}
}
