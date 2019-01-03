package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

	private BookingRepository bookingRepository;

	@Autowired
	public DatabaseSeeder(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		List<FlightBooking> flightbookings = new ArrayList<>();

		flightbookings.add(new FlightBooking("JetAirways", 604.00, "28-05-2018", "25-08-2018"));
		//flightbookings.add(new FlightBooking("EmiratesAirways", 804.00, "28-05-2018", "25-08-2018"));
		//flightbookings.add(new FlightBooking("BritishAirways", 654.35, "28-05-2018", "25-08-2018"));
		//flightbookings.add(new FlightBooking("KLMAirways", 689.75, "28-05-2018", "25-08-2018"));
		//flightbookings.add(new FlightBooking("EtihadAirways", 704.00, "28-05-2018", "25-08-2018"));

		bookingRepository.saveAll(flightbookings);

	}

}
