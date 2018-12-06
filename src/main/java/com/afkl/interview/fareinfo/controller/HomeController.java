package com.afkl.interview.fareinfo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.afkl.interview.fareinfo.domain.service.FareDetailsService;
import com.afkl.interview.fareinfo.domain.service.LocationDetailsService;
import com.afkl.interview.fareinfo.domain.service.RemoteAPIAuthenticationService;
import com.afkl.interview.fareinfo.dtos.locations.Location;


@Controller
public class HomeController {

	@Autowired
	RemoteAPIAuthenticationService remoteAPIAuthenticationService;

	@Autowired
	LocationDetailsService locationDetailsService;

	@Autowired
	FareDetailsService fareDetailsService;

	protected static final Logger LOGGER = LoggerFactory.getLogger(TravelApiController.class);

	@GetMapping("/home")
	public String loadLocations(Model model) {
		LOGGER.debug("Method Started {}", "loadLocations()");
		List<Location> resources = locationDetailsService.getSortedLocationsList("code");
		model.addAttribute("locations", resources);
		LOGGER.debug("Method End {}", "loadLocations()");
		return "welcome";
	}

}