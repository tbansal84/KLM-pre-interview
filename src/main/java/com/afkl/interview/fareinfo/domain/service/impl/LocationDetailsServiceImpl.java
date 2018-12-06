package com.afkl.interview.fareinfo.domain.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.afkl.interview.fareinfo.common.ApplicationConstants;
import com.afkl.interview.fareinfo.common.Pageable;
import com.afkl.interview.fareinfo.domain.service.LocationDetailsService;
import com.afkl.interview.fareinfo.domain.service.RemoteAPIAuthenticationService;
import com.afkl.interview.fareinfo.dtos.locations.Locations;
import com.afkl.interview.fareinfo.dtos.locations.EmbeddedObject;
import com.afkl.interview.fareinfo.dtos.locations.Location;
import com.afkl.interview.fareinfo.exceptions.TravelApiException;

@Service
public class LocationDetailsServiceImpl extends AbstractRemoteServiceClient implements LocationDetailsService {

	@Value("${remote.travel.api.location.service.endpoint}")
	private String remotefareServiceEndpoint;

	@Autowired
	RemoteAPIAuthenticationService remoteAPIAuthenticationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.afkl.interview.fareinfo.domain.service.LocationDetailsService#
	 * getLocationDetails(java.lang.String)
	 */
	@Override
	@Async(ApplicationConstants.threadPoolName)
	public CompletableFuture<Location> getLocationDetails(String location) {
		Location locationObj = null;
		try {
			locationObj = restTemplate.getForEntity(remoteBaseUrl + remotefareServiceEndpoint + "/" + location
					+ "?access_token=" + remoteAPIAuthenticationService.getToken(), Location.class).getBody();
//			LOGGER.debug("Response for getLocationService {} for location {}", airportsResponse.getBody(), location);
		} catch (Exception e) {
			LOGGER.error("Exception while calling remote service ", e);
			throw new TravelApiException(e);
		}
		return CompletableFuture.completedFuture(locationObj);
	}

	@Override
	public List<Location> getLocationsList(String searchcriteria) {
		List<Location> locationsList = null;
		Locations response = null;
		try {
			response = restTemplate.getForEntity(remoteBaseUrl + remotefareServiceEndpoint + "?access_token="
					+ remoteAPIAuthenticationService.getToken() + "&term=" + searchcriteria, Locations.class).getBody();
			locationsList = response.getLocations();
		} catch (RestClientException e) {
			LOGGER.error("Exception while calling remote service ", e);
			throw new TravelApiException(e);
		}

		return locationsList;
	}
	
	@Override
	public List<Location> getSortedLocationsList(String sortBy) {
		List<Location> locations = null;
		try {
			EmbeddedObject response = restTemplate.getForEntity(remoteBaseUrl + remotefareServiceEndpoint + "?access_token="
					+ remoteAPIAuthenticationService.getToken(), EmbeddedObject.class).getBody();
			locations = response.getEmbedded().getLocations();
			if (sortBy != null) {

				Collections.sort(locations, new Comparator<Location>() {
					@Override
					public int compare(Location o1, Location o2) {
						if (sortBy.equals("code")) {
							return o1.getCode().compareTo(o2.getCode());
						} else if (sortBy.equals("name")) {
							return o1.getName().compareTo(o2.getName());
						}
						return o1.getDescription().compareTo(o2.getDescription());
					}
				});
			}
		} catch (RestClientException e) {
			LOGGER.error("Exception while calling remote service ", e);
			throw new TravelApiException(e);
		}

		return locations;
	}

	@Override
	public PagedResources<Resource<Location>> getLocationsList(Pageable<Location> pagable, String sortBy) {
		PagedResources<Resource<Location>> locationsList = null;
		List<Location> locations = null;
		try {
			EmbeddedObject response = restTemplate.getForEntity(remoteBaseUrl + remotefareServiceEndpoint + "?access_token="
					+ remoteAPIAuthenticationService.getToken(), EmbeddedObject.class).getBody();
			locations = response.getEmbedded().getLocations();
			if (sortBy != null) {

				Collections.sort(locations, new Comparator<Location>() {
					@Override
					public int compare(Location o1, Location o2) {
						if (sortBy.equals("code")) {
							return o1.getCode().compareTo(o2.getCode());
						} else if (sortBy.equals("name")) {
							return o1.getName().compareTo(o2.getName());
						}
						return o1.getDescription().compareTo(o2.getDescription());
					}
				});
			}
			if (pagable != null) {
				locationsList = pagable.partition(locations);
			}
		} catch (RestClientException e) {
			LOGGER.error("Exception while calling remote service ", e);
			throw new TravelApiException(e);
		}

		return locationsList;
	}

}