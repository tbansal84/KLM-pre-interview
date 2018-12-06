package com.afkl.interview.fareinfo.dtos;

import java.io.Serializable;
import java.util.Currency;

import com.afkl.interview.fareinfo.dtos.locations.Location;

public class TripDetails implements Serializable {

	private double amount;
	private String currency;
	private Location origin;
	private Location destination;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

}
