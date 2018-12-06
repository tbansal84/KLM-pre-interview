package com.afkl.interview.fareinfo.domain.service.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.afkl.interview.fareinfo.common.ApplicationConstants;
import com.afkl.interview.fareinfo.domain.service.FareDetailsService;
import com.afkl.interview.fareinfo.domain.service.RemoteAPIAuthenticationService;
import com.afkl.interview.fareinfo.dtos.TravelFare;
import com.afkl.interview.fareinfo.dtos.TripDetails;
import com.afkl.interview.fareinfo.dtos.mapper.FareDetailsMapper;
import com.afkl.interview.fareinfo.exceptions.TravelApiException;

@Service
public class FareDetailsServiceImpl extends AbstractRemoteServiceClient implements FareDetailsService {
	@Value("${remote.travel.api.fare.service.endpoint}")
	private String remotefareServiceEndpoint;

	@Autowired
	RemoteAPIAuthenticationService remoteAPIAuthenticationService;

	protected static final Logger LOGGER = LoggerFactory.getLogger(FareDetailsServiceImpl.class);

	@Override
	@Async(ApplicationConstants.threadPoolName)
	public CompletableFuture<TripDetails> getTravelFare(String originCode, String destinationCode) {
		LOGGER.debug("Method Started {}", "getTravelFare");

		ResponseEntity<TravelFare> fareResponse = null;
		TripDetails details = null;
		try {
			fareResponse = restTemplate.getForEntity(remoteBaseUrl + remotefareServiceEndpoint + "/" + originCode + "/"
					+ destinationCode + "?access_token=" + remoteAPIAuthenticationService.getToken(), TravelFare.class);
			details=FareDetailsMapper.convertFareToTripDetails(fareResponse.getBody());
		} catch (Exception e) {
			LOGGER.error("Exception while calling remote service ", e);
			throw new TravelApiException(e);
		}
		return CompletableFuture.completedFuture(details);
	}
}