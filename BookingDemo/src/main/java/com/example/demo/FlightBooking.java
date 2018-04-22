package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FlightBooking {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String flightName;
	private double price;
	private String fromDate;
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
