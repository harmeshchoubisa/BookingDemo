package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

	@MockBean
	private BookingRepository bookingRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllBookings_basic() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/bookings/all").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				// .andExpect(content().json("{\"id\":1,\"flightName\":\"JetAirways\",\"price\":604.0,\"fromDate\":\"28-05-2018\",\"toDate\":\"25-08-2018\"}"))
				.andReturn();
		String resultjson = "[{\"id\":1,\"flightName\":\"JetAirways\",\"price\":604.0,\"fromDate\":\"28-05-2018\",\"toDate\":\"25-08-2018\"}]";
		JSONAssert.assertEquals(
				"[{\"id\":1,\"flightName\":\"JetAirways\",\"price\":604.0,\"fromDate\":\"28-05-2018\",\"toDate\":\"25-08-2018\"}]",
				resultjson, JSONCompareMode.LENIENT);

	}

	@Test
	public void getAffordableBooking() throws Exception {

		when(bookingRepository.findByPriceLessThan(Mockito.anyDouble()))
				.thenReturn(Arrays.asList(new FlightBooking("JetAirways", 604.0, "28-05-2018", "25-08-2018")));

		RequestBuilder request = MockMvcRequestBuilders.get("/bookings/affordable/{price}", 700)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
				"[{\"flightName\":\"JetAirways\",\"price\":604.0,\"fromDate\":\"28-05-2018\",\"toDate\":\"25-08-2018\"}]"))
				.andReturn();
		// JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
		// false);

	}

	@Test
	public void createBooking_basic() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		FlightBooking addedFlightDetails = new FlightBooking("newAirways", 800, "01-01-2019", "01-03-2019");
		when(bookingRepository.save(addedFlightDetails)).thenReturn(addedFlightDetails);

		RequestBuilder request = MockMvcRequestBuilders.post("/bookings/create").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(addedFlightDetails));

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void deleteBooking_basic() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/bookings/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

	}

}
