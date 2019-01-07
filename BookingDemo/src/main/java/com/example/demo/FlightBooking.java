package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class FlightBooking {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@NotNull
	@Size(min=2, message="Flight name should have atleast 2 characters")
    private String flightName;
	
	@NotNull
	@Min(1)
	private double price;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private String fromDate;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private String toDate;
	
	public FlightBooking() {}
	
	public FlightBooking(String flightName, double price, String fromDate, String toDate) {
		this.flightName = flightName;
		this.price = price;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}


	public String getFlightName() {
		return flightName;
	}


	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getFromDate() {
		return fromDate;
	}
	
	public long getId() {
		return id;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public String getToDate() {
		return toDate;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
