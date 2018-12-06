package com.afkl.interview.fareinfo.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afkl.interview.fareinfo.domain.service.ActuatorService;
import com.afkl.interview.fareinfo.dtos.ApiStatistics;
import com.afkl.interview.fareinfo.exceptions.TravelApiException;

@RestController
public class ActuatorController {

	@Autowired
	private ActuatorService actuatorService;

	protected static final Logger LOGGER = LoggerFactory.getLogger(ActuatorController.class);

	@GetMapping(value = "/stats", produces = "application/json; charset=UTF-8")
	public ApiStatistics getStats() {
		LOGGER.debug("Method Started {}", "findFare()");
		ApiStatistics stats = null;
		try {
			stats = actuatorService.getStatistics();
		} catch (Exception e) {
			LOGGER.error("Exception while calling futures ", e);
			throw new TravelApiException(e);
		}
		return stats;
	}

}
