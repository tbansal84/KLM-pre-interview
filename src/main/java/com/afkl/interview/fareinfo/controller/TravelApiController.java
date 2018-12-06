package com.afkl.interview.fareinfo.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afkl.interview.fareinfo.common.Pageable;
import com.afkl.interview.fareinfo.domain.service.FareDetailsService;
import com.afkl.interview.fareinfo.domain.service.LocationDetailsService;
import com.afkl.interview.fareinfo.domain.service.RemoteAPIAuthenticationService;
import com.afkl.interview.fareinfo.dtos.TripDetails;
import com.afkl.interview.fareinfo.dtos.locations.Location;
import com.afkl.interview.fareinfo.exceptions.TravelApiException;


@RestController()
@RequestMapping(value = "/travel", produces = "application/json; charset=UTF-8")
public class TravelApiController {

	@Autowired
	RemoteAPIAuthenticationService remoteAPIAuthenticationService;

	@Autowired
	LocationDetailsService locationDetailsService;

	@Autowired
	FareDetailsService fareDetailsService;

	protected static final Logger LOGGER = LoggerFactory.getLogger(TravelApiController.class);


	@GetMapping(value = "/plan/{origin}/{destination}")
	public TripDetails findFare(@PathVariable("origin") final String origin,
			@PathVariable("destination") final String destination) {
		LOGGER.debug("Method Started {}", "findFare()");
		TripDetails details = null;
		CompletableFuture<TripDetails> airfareDetailsFuture = fareDetailsService.getTravelFare(origin, destination);
		CompletableFuture<Location> originDetailsFuture = locationDetailsService.getLocationDetails(origin);
		CompletableFuture<Location> destinationDetailsFuture = locationDetailsService.getLocationDetails(destination);
		CompletableFuture.allOf(airfareDetailsFuture, originDetailsFuture, destinationDetailsFuture);
		try {
			details = airfareDetailsFuture.get();
			details.setDestination(destinationDetailsFuture.get());
			details.setOrigin(originDetailsFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error("Exception while calling futures ", e);
			throw new TravelApiException(e);
		}
		return details;
	}

	@GetMapping(path = "/location/{searchcriteria}")
	public List<Location> getDestionationAirports(@PathVariable String searchcriteria) {
		return locationDetailsService.getLocationsList(searchcriteria);
	}

	@GetMapping(path = "/location")
	public PagedResources<Resource<Location>> getDestionationAirports(Pageable<Location> pageable,
			@RequestParam(required = false) String sortBy) {
		return locationDetailsService.getLocationsList(pageable, sortBy);
	}

}
