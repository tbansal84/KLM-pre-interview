package com.afkl.interview.fareinfo.dtos.mapper;

import org.json.simple.parser.ParseException;

import com.afkl.interview.fareinfo.dtos.TravelFare;
import com.afkl.interview.fareinfo.dtos.TripDetails;

public class FareDetailsMapper {

	public static TripDetails convertFareToTripDetails(TravelFare travelFare) throws ParseException {

		TripDetails details = new TripDetails();
		details.setAmount(travelFare.getAmount());
		details.setCurrency(travelFare.getCurrency());
		return details;

	}

}
